package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
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
    @PostMapping
    public ResultForm attendance(@LoginUser Users loggedInUser, @RequestParam("lectureCode") Long lectureCode) {
        return attendanceService.attend((Students) loggedInUser, lectureCode);
    }

    @GetMapping
    public ResultForm getAllAttendanceList(@LoginUser Users loggedInUser) {
        Map<String, Map<Integer, Long>> allAttendanceList = attendanceService.getLectureAttendanceCounts((Students)loggedInUser);
        return ResultFormUtils.getSuccessResultForm(allAttendanceList);
    }
}
