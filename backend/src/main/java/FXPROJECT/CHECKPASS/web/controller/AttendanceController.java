package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    /**
     * 출석체크
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     * @return 성공 : (출석 : 출석체크가 완료되었습니다. 지각 : 지각 처리되었습니다.), 실패 : 출석체크 시간이 아닙니다.
     */
    @PostMapping("/{lectureCode}")
    public ResultForm attendance(@LoginUser Users loggedInUser, @PathVariable("lectureCode") Long lectureCode) {
        if (!isStudent(loggedInUser)) {
            throw new NoPermission();
        }

        return attendanceService.attend((Students) loggedInUser, lectureCode);
    }

    /**
     * 전자출석
     * @param loggedInUser 로그인된 유저
     * @param attendanceCode 출석코드
     * @return 성공 : 출석체크가 완료되었습니다  실패 : 출석체크 시간이 아닙니다. 또는 출석코드가 일치하지 않습니다.
     */
    @PostMapping
    public ResultForm attendance(@LoginUser Users loggedInUser, @RequestParam("attendanceCode") int attendanceCode) {
        if (!isStudent(loggedInUser)) {
            throw new NoPermission();
        }

        return attendanceService.attend((Students) loggedInUser, attendanceCode);
    }

    /**
     * 모든 강의 출석현황 통계보기
     * @param loggedInUser 로그인된 유저
     * @return 사용자의 수강하고 있는 강의 출석현황 통계 Map
     */
    @GetMapping
    public ResultForm getAllLectureAttendanceStats(@LoginUser Users loggedInUser) {
        if (!isStudent(loggedInUser)) {
            throw new NoPermission();
        }

        Map<String, Map<Integer, Long>> allAttendanceList = attendanceService.getAllLectureAttendanceCounts((Students)loggedInUser);
        return ResultFormUtils.getSuccessResultForm(allAttendanceList);
    }

    /**
     * 특정 강의의 출석현황 보기
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     * @return 각 주차마다 출석상테가 담겨있는 Map
     */
    @GetMapping("/{lectureCode}")
    public ResultForm getAttendanceList(@LoginUser Users loggedInUser, @PathVariable("lectureCode") Long lectureCode) {
        if (!isStudent(loggedInUser)) {
            throw new NoPermission();
        }

        Map<Integer, String> attendanceStatusMap = attendanceService.getLectureAttendanceCounts((Students)loggedInUser, lectureCode);
        return ResultFormUtils.getSuccessResultForm(attendanceStatusMap);
    }

    /**
     * 생성한 출석코드 보내주기
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     * @return 생성한 출석코드 객체 정보
     */
    @PostMapping("/token/{lectureCode}")
    public ResultForm getAttendanceToken(@LoginUser Users loggedInUser, @PathVariable("lectureCode") Long lectureCode) {
        if (!isProfessorOrStaff(loggedInUser)) {
            throw new NoPermission();
        }

        return attendanceService.generateAttendanceToken(lectureCode);
    }

    private boolean isProfessorOrStaff(Users loggedInUser) {
        Job job = loggedInUser.getUserJob();

        if (job == Job.STUDENTS) {
            return false;
        }

        return true;
    }

    private boolean isStudent(Users loggedInUser) {
        Job job = loggedInUser.getUserJob();

        if (job != Job.STUDENTS) {
            return false;
        }

        return true;
    }
}
