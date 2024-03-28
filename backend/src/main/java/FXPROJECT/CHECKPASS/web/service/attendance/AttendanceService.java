package FXPROJECT.CHECKPASS.web.service.attendance;

import FXPROJECT.CHECKPASS.domain.common.exception.*;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.entity.attendance.Attendance;
import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceId;
import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceTokens;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.attendance.JpaAttendanceRepository;
import FXPROJECT.CHECKPASS.domain.repository.attendance.JpaAttendanceTokenRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaEnrollmentRepository;
import FXPROJECT.CHECKPASS.web.common.utils.LectureWeekUtils;
import FXPROJECT.CHECKPASS.web.common.utils.RandomNumberUtils;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.attendance.AttendanceInputForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.AttendanceInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.AttendanceTokenInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final UserService userService;
    private final LectureService lectureService;
    private final LectureWeekUtils lectureWeekUtils;
    private final JpaAttendanceTokenRepository jpaAttendanceTokenRepository;
    private final JpaAttendanceRepository jpaAttendanceRepository;
    private final JpaEnrollmentRepository jpaEnrollmentRepository;
    private final QueryRepository queryRepository;
    private final RandomNumberUtils randomNumberUtils;
    private final ConversionService conversionService;

    /**
     * 출석체크
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     * @return 성공 : (출석 : 출석체크가 완료되었습니다. 지각 : 지각 처리되었습니다.), 실패 : 출석체크 시간이 아닙니다.
     */
    @Transactional
    public ResultForm attend(Students loggedInUser, Long lectureCode) {
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime().truncatedTo(ChronoUnit.MINUTES);

        Lecture lecture = lectureService.getLecture(lectureCode);
        List<LectureTimeCode> lectureTimeCodeList = lecture.getLectureTimeCode();

        AttendanceId attendanceId = generateAttendanceId(loggedInUser, lectureCode);

        if (!isExistsInEnrollment(attendanceId)){
            throw new DoNotTakeTheCourse();
        }

        if (isAttendanceChecked(attendanceId)) {
            throw new AttendanceAlreadyProcessed();
        }

        for (LectureTimeCode lectureTimeCode : lectureTimeCodeList) {
            if (isCurrentLectureDay(lectureTimeCode)) {
                return checkAndSaveAttendance(attendanceId, currentTime, lectureTimeCode);
            }
        }
        throw new NotAttendanceCheckTime();
    }

    /**
     * 전자출석
     * @param loggedInUser 로그인된 유저
     * @param attendanceCode 출석코드
     * @return 성공 : 출석체크가 완료되었습니다  실패 : 출석체크 시간이 아닙니다. 또는 출석코드가 일치하지 않습니다.
     */
    @Transactional
    public ResultForm attend(Students loggedInUser, Long lectureCode, int attendanceCode) {
        Lecture lecture = lectureService.getLecture(lectureCode);
        AttendanceTokens attendanceToken = jpaAttendanceTokenRepository.findByLecture(lecture);
        AttendanceId attendanceId = generateAttendanceId(loggedInUser, lectureCode);

        if (!isExistsInEnrollment(attendanceId)) {
            throw new DoNotTakeTheCourse();
        }

        if (attendanceCode != attendanceToken.getAttendanceCode()) {
            throw new AttendanceCodeMismatch();
        }

        if (isAttendanceChecked(attendanceId)) {
            throw new AttendanceAlreadyProcessed();
        }

        saveAttendance(attendanceId, 1); // 출석

        return ResultFormUtils.getSuccessResultForm(COMPLETE_ATTENDANCE.getDescription());
    }

    /**
     * 모든 강의 출석현황 통계 구하기
     * @param loggedInUser 로그인된 유저
     * @return 사용자의 수강하고 있는 강의 출석현황 통계 Map
     */
    public Map<String, Map<Integer, Long>> getAllLectureAttendanceCounts(Students loggedInUser) {
        Long studentId = loggedInUser.getUserId();
        String studentGrade = loggedInUser.getStudentGrade();
        String studentSemester = loggedInUser.getStudentSemester();

        Map<String, Map<Integer, Long>> lectureAttendanceCounts = new TreeMap<>();

        List<Lecture> enrollmentList = queryRepository.getEnrollmentList(loggedInUser);
        for (Lecture lecture : enrollmentList) {
            String lectureName = lecture.getLectureName();
            Long lectureCode = lecture.getLectureCode();

            List<Tuple> attendanceCountList = queryRepository.getAttendanceCountList(studentId, lectureCode, studentGrade, studentSemester);
            Map<Integer, Long> attendanceCounts = aggregateAndSortAttendanceCounts(attendanceCountList);
            lectureAttendanceCounts.put(lectureName, attendanceCounts);
        }
        return lectureAttendanceCounts;
    }

    /**
     * 특정 강의의 출석현황 구하기
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     * @return 각 주차마다 출석현황이 담겨져 있는 Map
     */
    public List<String> getLectureAttendanceCountList(Students loggedInUser, Long lectureCode) {
        Long studentId = loggedInUser.getUserId();
        String studentGrade = loggedInUser.getStudentGrade();
        String studentSemester = loggedInUser.getStudentSemester();

        int maxWeeks = 16; // 16주차
        List<String> lectureAttendanceStatusList = new ArrayList<>(Collections.nCopies(maxWeeks, ""));

        List<Attendance> attendanceList = queryRepository.getAttendanceList(studentId, lectureCode, studentGrade, studentSemester);

        for (Attendance attendance : attendanceList) {
            int attendanceWeek = attendance.getAttendanceId().getWeek() - 1; // 리스트 인덱스 0부터 시작해야하므로 1을 뺌
            String status = String.valueOf(attendance.getAttendanceStatus()); // 출석 상태를 문자열로 변환
            String existingStatus = lectureAttendanceStatusList.get(attendanceWeek);
            lectureAttendanceStatusList.set(attendanceWeek, existingStatus + status);
        }

        return lectureAttendanceStatusList;
    }

    /**
     * 현재 출석인원 목록 구하기
     * @param lectureCode 강의코드
     * @return 현재 출석한 인원 목록 Map
     */
    public Map<Long, String> getPresentAttendanceUsers(Long lectureCode) {
        Map<Long, String> presentAttendanceUsers = new TreeMap<>();

        int week = lectureWeekUtils.getWeek(); // 현재 주차
        String day = String.valueOf(LocalDateTime.now().getDayOfWeek().getValue() - 1); // 월(0) ~ 금(5)

        List<Attendance> attendanceList = queryRepository.getPresentAttendanceList(lectureCode, day, week);

        for(Attendance attendance : attendanceList) {
            Long studentId = attendance.getAttendanceId().getStudentId();
            Users user = userService.getUser(studentId);
            String userName = user.getUserName();

            presentAttendanceUsers.put(studentId, userName);
        }

        return presentAttendanceUsers;
    }

    /**
     * 특정 강의의 해당 주차에 따른 학생들의 출석 정보 목록 구하기
     * @param lectureCode 강의코드
     * @param week 주차
     * @return 학생들의 출석 정보 목록
     */
    public List<AttendanceInformation> getStudentAttendanceInformationList(Long lectureCode, int week) {
        List<AttendanceInformation> studentAttendanceInformationList = new ArrayList<>();
        Map<Long, String> attendanceStatusByStudentId = new TreeMap<>();

        List<Attendance> attendanceList = queryRepository.getAttendanceListByLectureAndWeek(lectureCode, week);

        for (Attendance attendance : attendanceList) {
            Long studentId = attendance.getAttendanceId().getStudentId();
            String status = String.valueOf(attendance.getAttendanceStatus());

            if (attendanceStatusByStudentId.containsKey(studentId)) {
                String existingStatus = attendanceStatusByStudentId.get(studentId);
                String saveStatus = existingStatus + status;
                attendanceStatusByStudentId.put(studentId, saveStatus);
            } else {
                attendanceStatusByStudentId.put(studentId, status);
            }
        }

        for (Map.Entry<Long, String> entry : attendanceStatusByStudentId.entrySet()) {
            Long studentId = entry.getKey();
            Users user = userService.getUser(studentId);
            String studentName = user.getUserName();
            String attendanceStatus = entry.getValue();

            AttendanceInformation attendanceInformation = new AttendanceInformation(studentId, studentName, attendanceStatus);
            studentAttendanceInformationList.add(attendanceInformation);
        }

        return studentAttendanceInformationList;
    }

    /**
     * 출석코드 생성하기
     * @param lectureCode 강의코드
     */
    @Transactional
    public void generateAttendanceToken(Long lectureCode) {
        Lecture lecture = lectureService.getLecture(lectureCode);
        List<LectureTimeCode> lectureTimeCodeList = lecture.getLectureTimeCode();

        for (LectureTimeCode lectureTimeCode : lectureTimeCodeList) {
            if (isCurrentLectureDay(lectureTimeCode)) {
                int attendanceCode = randomNumberUtils.generateAttendanceCode(); // 출결코드 생성

                LocalDateTime now = LocalDateTime.now();
                LocalDateTime startDate = now.truncatedTo(ChronoUnit.SECONDS);

                AttendanceTokens attendanceToken = new AttendanceTokens(lecture, attendanceCode, startDate);
                jpaAttendanceTokenRepository.save(attendanceToken);
            }
        }

        throw new NotAttendanceCheckTime();
    }

    /**
     * 출석코드 보내주기
     * @param lectureCode 강의코드
     * @return AttendanceTokens 객체
     */
    public AttendanceTokenInformation getAttendanceToken(Long lectureCode) {
        Lecture lecture = lectureService.getLecture(lectureCode);

        if (!jpaAttendanceTokenRepository.existsByLecture(lecture)) {
            throw new NoSuchAttendanceToken();
        }

        AttendanceTokens attendanceToken = jpaAttendanceTokenRepository.findByLecture(lecture);
        AttendanceTokenInformation attendanceTokenInformation = conversionService.convert(attendanceToken, AttendanceTokenInformation.class);

        return attendanceTokenInformation;
    }

    /**
     * 결석 처리하기(수동)
     * @param form 유저Id, 강의 코드가 담긴 form
     */
    @Transactional
    public void setAbsent(AttendanceInputForm form) {
        Long studentId = form.getUserId();
        Long lectureCode = form.getLectureCode();

        Students student = (Students) userService.getUser(studentId);
        AttendanceId attendanceId = generateAttendanceId(student, lectureCode);
        queryRepository.setAbsent(attendanceId);
    }

    /**
     * 지각 처리하기(수동)
     * @param form 유저Id, 강의 코드가 담긴 form
     */
    @Transactional
    public void setLateness(AttendanceInputForm form) {
        Long studentId = form.getUserId();
        Long lectureCode = form.getLectureCode();

        Students student = (Students) userService.getUser(studentId);
        AttendanceId attendanceId = generateAttendanceId(student, lectureCode);
        queryRepository.setLateness(attendanceId);
    }

    /**
     * 출석 처리하기(수동)
     * @param form 유저Id, 강의 코드가 담긴 form
     */
    @Transactional
    public void setAttend(AttendanceInputForm form) {
        Long studentId = form.getUserId();
        Long lectureCode = form.getLectureCode();

        Students student = (Students) userService.getUser(studentId);
        AttendanceId attendanceId = generateAttendanceId(student, lectureCode);
        queryRepository.setAttend(attendanceId);
    }

    private boolean isCurrentLectureDay(LectureTimeCode lectureTimeCode) {
        // TO-BE : 현재 날짜와 강의 날짜가 서로 같은지 확인
        String day = String.valueOf(LocalDateTime.now().getDayOfWeek().getValue() - 1); // 월(0) ~ 금(5)
        String timeCode = lectureTimeCode.getLectureTimeCode();
        String lectureDay = timeCode.substring(1, 2);
        return day.equals(lectureDay);
    }

    private ResultForm checkAndSaveAttendance(AttendanceId attendanceId, LocalTime currentTime, LectureTimeCode lectureTimeCode) {
        // TO-BE : 시간을 확인하여 각 상황에 맞는 출석정보 저장
        String timeCode = lectureTimeCode.getLectureTimeCode();
        int lectureHour = Integer.parseInt(timeCode.substring(3, 5));
        int lectureMinute = Integer.parseInt(timeCode.substring(5, 7));

        LocalTime startTime = calculateStartTime(lectureHour, lectureMinute);
        LocalTime endTime = calculateEndTime(lectureHour, lectureMinute);
        LocalTime latenessStartTime = endTime.minusMinutes(1); // 출석시간 후 10분간 지각 처리, 그 이후는 결석 처리
        LocalTime latenessEndTime = endTime.plusMinutes(10);

        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            saveAttendance(attendanceId, 1); // 출석 처리
            return ResultFormUtils.getSuccessResultForm(COMPLETE_ATTENDANCE.getDescription());
        } else if (currentTime.isAfter(latenessStartTime) && currentTime.isBefore(latenessEndTime)){
            saveAttendance(attendanceId, 2); // 지각 처리
            return ResultFormUtils.getSuccessResultForm(TREAT_LATENESS.getDescription());
        }
        throw new NotAttendanceCheckTime();
    }

    private LocalTime calculateStartTime(int lectureHour, int lectureMinute) {
        // TO-BE : 비콘 출석시작 시간 계산
        // 수업시간 시작 10분 전, 학칙에 의거 수업시간 시작 후 20분 이내이면 출석
        return lectureMinute == 0 ? LocalTime.of(lectureHour - 1, 49) : LocalTime.of(lectureHour, 19);
    }

    private LocalTime calculateEndTime(int lectureHour, int lectureMinute) {
        // TO-BE : 비콘 출석종료 시간 계산
        return lectureMinute == 0 ? LocalTime.of(lectureHour, 21) : LocalTime.of(lectureHour, 51);
    }

    private void saveAttendance(AttendanceId attendanceId, int status) {
        // TO-BE : 출석정보 저장
        Attendance attendance = new Attendance(attendanceId, status);
        jpaAttendanceRepository.save(attendance);
    }

    private Map<Integer, Long> aggregateAndSortAttendanceCounts(List<Tuple> attendanceCountList){
        // TO-BE : 각 출석 상태별로 출석 횟수를 집계하고, 그 결과를 오름차순으로 정렬된 Map으로 반환
        Map<Integer, Long> attendanceCounts = new TreeMap<>();

        for (int i = 1; i < 4; i++) {
            attendanceCounts.put(i, 0L);
        }

        for (Tuple attendanceCount : attendanceCountList) {
            Integer attendanceCode = attendanceCount.get(0, Integer.class);

            if (attendanceCode == 0) {
                continue;
            }

            Long count = attendanceCount.get(1, Long.class);
            attendanceCounts.put(attendanceCode, count);
        }
        return attendanceCounts;
    }

    private boolean isAttendanceChecked(AttendanceId attendanceId) {
        if (!jpaAttendanceRepository.existsByAttendanceStatus(attendanceId)) {
            return false;
        }

        return true;
    }

    private boolean isExistsInEnrollment(AttendanceId attendanceId){
        Long lectureCode = attendanceId.getLectureCode();

        if (!jpaEnrollmentRepository.existsByLectureCode(lectureCode)) {
            return false;
        }

        return true;
    }

    private AttendanceId generateAttendanceId(Students loggedInUser, Long lectureCode) {
        Long studentId = loggedInUser.getUserId();
        String studentGrade = loggedInUser.getStudentGrade();
        String studentSemester = loggedInUser.getStudentSemester();

        int week = lectureWeekUtils.getWeek(); // 현재 주차
        String day = String.valueOf(LocalDateTime.now().getDayOfWeek().getValue() - 1); // 월(0) ~ 금(5)

        AttendanceId attendanceId = new AttendanceId(studentId, lectureCode, studentGrade, studentSemester, day, week);

        return attendanceId;
    }

}