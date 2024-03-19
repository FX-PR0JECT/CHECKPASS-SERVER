package FXPROJECT.CHECKPASS.web.service.attendance;

import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.entity.attendance.Attendance;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.attendance.JpaAttendanceRepository;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceWeekService {

    private final JpaAttendanceRepository jpaAttendanceRepository;
    private final QueryRepository queryRepository;
    private final LectureService lectureService;

    /**
     * 출석주차 생성
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     */
    public void generateAttendanceWeek(Users loggedInUser, Long lectureCode) {

        Students student = (Students)loggedInUser;
        Long studentId = student.getUserId();
        String studentGrade = student.getStudentGrade().substring(0, 1);
        String studentSemester = student.getStudentSemester().substring(0, 1);

        Lecture lecture = lectureService.getLecture(lectureCode);
        List<LectureTimeCode> lectureTimeCodeList = lecture.getLectureTimeCode();
        List<String> lectureDays = new ArrayList<>();
        for (LectureTimeCode lectureTimeCode : lectureTimeCodeList) {
            String day = lectureTimeCode.getLectureTimeCode().substring(1, 2);
            lectureDays.add(day);
        }

        for (int i = 1; i < 17; i++) {
            for (String lectureDay : lectureDays) {
                String attendanceId = studentId.toString() + lectureCode.toString() + studentGrade + studentSemester + lectureDay + 0 + i;
                Attendance attendance = new Attendance(attendanceId, 0);
                jpaAttendanceRepository.save(attendance);
            }
        }
    }

    /**
     * 출석주차 삭제
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     */
    public void deleteAttendanceWeek(Users loggedInUser, Long lectureCode) {

        Students student = (Students)loggedInUser;
        Long studentId = student.getUserId();
        String studentGrade = student.getStudentGrade().substring(0, 1);
        String studentSemester = student.getStudentSemester().substring(0, 1);

        queryRepository.deleteAttendanceWeek(studentId, lectureCode, studentGrade, studentSemester);
    }
}
