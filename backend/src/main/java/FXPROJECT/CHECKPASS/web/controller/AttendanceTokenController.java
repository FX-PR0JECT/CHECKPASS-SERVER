package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.AttendanceTokenInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attendanceTokens")
public class AttendanceTokenController {

    private final AttendanceService attendanceService;

    /**
     * 해당 강의의 AttendanceToken 정보 조회
     * @param loggedInUser 로그인한 유저
     * @param lectureCode 강의코드
     * @return 해당 강의의 AttendanceTokenInformation 객체 정보
     */
    @GetMapping("{lectureCode}")
    public ResultForm getAttendanceToken(@LoginUser Users loggedInUser, @PathVariable("lectureCode") Long lectureCode) {
        if (!isProfessorOrStaff(loggedInUser)) {
            throw new NoPermission();
        }

        AttendanceTokenInformation attendanceTokenInformation = attendanceService.getAttendanceToken(lectureCode);

        return ResultFormUtils.getSuccessResultForm(attendanceTokenInformation);
    }

    /**
     * AttendanceTokens 생성 요청
     * @param loggedInUser 로그인된 유저
     * @param lectureCode 강의코드
     * @return redirect attendance/getToken/{lectureCode}
     */
    @PostMapping("/{lectureCode}")
    public ResultForm generateAttendanceToken(@LoginUser Users loggedInUser, @PathVariable("lectureCode") Long lectureCode) {
        if (!isProfessorOrStaff(loggedInUser)) {
            throw new NoPermission();
        }

        attendanceService.generateAttendanceToken(lectureCode);

        return getAttendanceToken(loggedInUser, lectureCode);
    }

    private boolean isProfessorOrStaff(Users loggedInUser) {
        Job job = loggedInUser.getUserJob();

        if (job == Job.STUDENTS) {
            return false;
        }

        return true;
    }
}
