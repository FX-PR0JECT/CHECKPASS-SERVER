package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.common.exception.*;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.dto.ScheduleArray;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Enrollment;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaEnrollmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.common.utils.ToLectureWordUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.attendance.AttendanceWeekService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final JpaEnrollmentRepository jpaEnrollmentRepository;
    private final LectureService lectureService;
    private final JpaLectureRepository jpaLectureRepository;
    private final QueryRepository queryRepository;
    private final ConversionService conversionService;
    private final AttendanceWeekService attendanceWeekService;

    /**
     * 수강신청
     * @param lectureCode 강의코드
     * @param loggedInUser 로그인유저
     * @return 성공 : 신청완료 ResultForm  실패 : 신청실패 ResultForm
     */
    @Transactional
    public ResultForm enrollment(Long lectureCode, Users loggedInUser){

        if (!lectureService.existsLecture(lectureCode)){
            throw new NonExistentLecture();
        }

        Lecture target = jpaLectureRepository.findLectureByLectureCode(lectureCode);

        Enrollment enrollment = new Enrollment((Students) loggedInUser, target);
        if (jpaEnrollmentRepository.existsById(enrollment.getEnrollmentId())) {
            throw new RegisteredForLecture();
        }

        if (!checkLectureTime(target, loggedInUser)) {
            throw new OverlappingHours();
        }

        if (target.getLectureFull() == target.getLectureCount()){
            throw new NumberOfStudentsExceeded();
        }

        jpaEnrollmentRepository.save(enrollment);
        attendanceWeekService.generateAttendanceWeek(loggedInUser, lectureCode);
        target.setLectureCount(target.getLectureCount() + 1);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_ENROLLMENT.getDescription());
    }


    /**
     * 수강신청 취소
     * @param lectureCode 강의 코드
     * @param loggedInUser 로그인유저
     * @return 성공 : 삭제 완료 ResultForm  실패 : 잘못된 요청 ResultForm
     */
    @Transactional
    public ResultForm cancelEnrollment(Long lectureCode, Users loggedInUser){

        Long enrollmentId = idGenerator(lectureCode, loggedInUser);

        if (!jpaEnrollmentRepository.existsById(enrollmentId)){
            return ResultFormUtils.getFailResultForm(NO_COURSE_HISTORY);
        }

        attendanceWeekService.deleteAttendanceWeek(loggedInUser, lectureCode);
        jpaEnrollmentRepository.deleteById(enrollmentId);
        lectureService.decreaseLectureCount(lectureCode);

        return ResultFormUtils.getSuccessResultForm(COURSE_CANCELLATION_COMPLETED.getDescription());
    }


    /**
     * 수강신청 목록 조회
     * @param loggedInUser 로그인 유저
     * @return 로그인 유저의 수강신청한 강의 목록
     */
    public List<LectureInformation> getEnrollmentList(Users loggedInUser){

        List<Lecture> enrollmentList =  queryRepository.getEnrollmentList((Students) loggedInUser);
        if(enrollmentList.isEmpty()) {
            throw new NoCourseHistory();
        }

        List<LectureInformation> lectureInformationList = new ArrayList<>();

        for (Lecture lecture : enrollmentList) {
            LectureInformation lectureInformation = conversionService.convert(lecture, LectureInformation.class);
            lectureInformationList.add(lectureInformation);
        }
        return lectureInformationList;
    }

    /**
     * 수강이력 조회
     * @param loggedInUser 로그인 유저
     * @return 학생의 연도학기별 수강이력
     */
    public Map<String, List<LectureInformation>> getCourseList(Users loggedInUser){

        Comparator<String> comparator = Collections.reverseOrder();
        Map<String, List<LectureInformation>> enrollmentList = new TreeMap<>(comparator);

        List<String> yearSemesterListOfStudent = queryRepository.getYearSemesterList((Students) loggedInUser);
        List<Enrollment> courseListOfStudent= queryRepository.getCourseList((Students)loggedInUser);

        for (String yearSemester : yearSemesterListOfStudent) {

            List<LectureInformation> lectureList = new ArrayList<>();

            for (Enrollment enrollment : courseListOfStudent) {
                if (yearSemester.equals(enrollment.getYearSemester())){

                    Lecture lecture = enrollment.getLecture();
                    LectureInformation lectureInformation = conversionService.convert(lecture, LectureInformation.class);

                    lectureList.add(lectureInformation);
                }
            }
            enrollmentList.put(yearSemester, lectureList);
        }

        return enrollmentList;
    }

    private Long idGenerator(Long lectureCode, Users loggedInUser){
        return Long.valueOf(loggedInUser.getUserId().toString() + lectureCode.toString());
    }

    private boolean checkLectureTime(Lecture target, Users loggedInUser){

        List<Lecture> enrollmentList = queryRepository.getEnrollmentList((Students)loggedInUser);
        if (enrollmentList.isEmpty()) {
            return true;
        }
        Map<String, boolean[]> fullScheduleArray = generateFullScheduleArray(enrollmentList);

        List<LectureTimeCode> timeCodeList = target.getLectureTimeCode();
        ScheduleArray targetSchedule = ToLectureWordUtils.getScheduleArray(timeCodeList);
        Map<String, boolean[]> targetScheduleArray = targetSchedule.getScheduleArray();

        for (String day : targetScheduleArray.keySet()){
            if (fullScheduleArray.containsKey(day)) {
                boolean[] studentSchedule = fullScheduleArray.get(day);
                boolean[] lectureSchedule = targetScheduleArray.get(day);

                for (int i = 0; i < studentSchedule.length; i++) {
                    if (studentSchedule[i] && lectureSchedule[i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Map<String, boolean[]> generateFullScheduleArray(List<Lecture> lectureList){

        Map<String, boolean[]> fullScheduleArray = new HashMap<>();

        for (Lecture lecture : lectureList) {
            List<LectureTimeCode> lectureTimeCodeList = lecture.getLectureTimeCode();
            ScheduleArray studentSchedule = ToLectureWordUtils.getScheduleArray(lectureTimeCodeList);
            Map<String, boolean[]> studentScheduleArray = studentSchedule.getScheduleArray();

            for (String day : studentScheduleArray.keySet()){
                fullScheduleArray.merge(day, studentScheduleArray.get(day), EnrollmentService::sumBooleanArray);
            }
        }
        return fullScheduleArray;
    }

    private static boolean[] sumBooleanArray(boolean[] original, boolean[] operand) {
        for (int i = 0; i < original.length; i++) {
            original[i] = original[i] || operand[i];
        }
        return original;
    }
}