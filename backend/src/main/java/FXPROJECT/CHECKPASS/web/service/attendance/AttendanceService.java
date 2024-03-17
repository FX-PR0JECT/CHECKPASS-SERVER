package FXPROJECT.CHECKPASS.web.service.attendance;

import FXPROJECT.CHECKPASS.domain.common.exception.NotAttendanceCheckTime;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.entity.attendance.Attendance;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.attendance.JpaAttendanceRepository;
import FXPROJECT.CHECKPASS.web.common.utils.LectureWeekUtils;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final LectureService lectureService;
    private final LectureWeekUtils lectureWeekUtils;
    private final JpaAttendanceRepository jpaAttendanceRepository;
    private final QueryRepository queryRepository;

    /**
     * 출석체크
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     * @return 성공 : (출석 : 출석체크가 완료되었습니다. 지각 : 지각 처리되었습니다.), 실패 : 출석체크 시간이 아닙니다.
     */
    public ResultForm attend(Students loggedInUser, Long lectureCode) {
        Long userId = loggedInUser.getUserId();
        String studentGrade = loggedInUser.getStudentGrade().substring(0, 1);
        String studentSemester = loggedInUser.getStudentSemester().substring(0, 1);

        int week = lectureWeekUtils.getWeek(); // 현재 주차
        String day = String.valueOf(LocalDateTime.now().getDayOfWeek().getValue() - 1); // 월(0) ~ 금(5)

        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime().truncatedTo(ChronoUnit.MINUTES);

        Lecture lecture = lectureService.getLecture(lectureCode);
        List<LectureTimeCode> lectureTimeCodeList = lecture.getLectureTimeCode();

        String attendanceId = userId.toString() + lectureCode.toString() + studentGrade + studentSemester + week + day;

        for (LectureTimeCode lectureTimeCode : lectureTimeCodeList) {
            if (isCurrentLectureDay(day, lectureTimeCode)) {
                return checkAndSaveAttendance(attendanceId, currentTime, lectureTimeCode);
            }
        }
        throw new NotAttendanceCheckTime();
    }

    private boolean isCurrentLectureDay(String day, LectureTimeCode lectureTimeCode) {
        // TO-BE : 현재 날짜와 강의 날짜가 서로 같은지 확인
        String timeCode = lectureTimeCode.getLectureTimeCode();
        String lectureDay = timeCode.substring(1, 2);
        return day.equals(lectureDay);
    }

    private ResultForm checkAndSaveAttendance(String attendanceId, LocalTime currentTime, LectureTimeCode lectureTimeCode) {
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
        } else {
            throw new NotAttendanceCheckTime();
        }
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

    private void saveAttendance(String attendanceId, int status) {
        // TO-BE : 출석정보 저장
        Attendance attendance = new Attendance(attendanceId, status);
        jpaAttendanceRepository.save(attendance);
    }
}
