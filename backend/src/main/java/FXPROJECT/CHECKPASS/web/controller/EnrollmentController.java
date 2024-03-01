package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.NoPermissionToEnrollment;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.lectures.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * 수강신청
     * URL : /enrollment/{lectureCode}
     * @param lectureCode 강의코드
     * @param loggedInUser 로그인 유저
     * @return 성공 : 수강신청 완료 ResultForm  실패 : 수강신청 실패 ResultForm
     */
    @PostMapping("/{lectureCode}")
    public ResultForm enrollment(@PathVariable("lectureCode") Long lectureCode, @LoginUser Users loggedInUser){

        if (loggedInUser.getUserJob() != Job.STUDENTS) {
            throw new NoPermissionToEnrollment();
        }

        return enrollmentService.enrollment(lectureCode, loggedInUser);
    }


    /**
     * 수강신청 취소
     * @param lectureCode 강의코드
     * @param loggedInUser 로그인 유저
     * @return 성공 : 취소 완료 ResultForm  실패 : 잘못된 요청 ResultForm
     */
    @DeleteMapping("{lectureCode}")
    public ResultForm cancelEnrollment(@PathVariable("lectureCode") Long lectureCode, @LoginUser Users loggedInUser){

        if (loggedInUser.getUserJob() != Job.STUDENTS) {
            throw new NoPermissionToEnrollment();
        }
        return enrollmentService.cancelEnrollment(lectureCode, loggedInUser);
    }


    /**
     * 수강신청 목록 조회
     * @param loggedInUser 로그인 유저
     * @return 로그인 유저가 수강신청한 강의 정보 목록
     */
    @GetMapping
    public ResultForm getEnrollmentList(@LoginUser Users loggedInUser){
        return ResultFormUtils.getSuccessResultForm(enrollmentService.getEnrollmentList(loggedInUser));
    }


    /**
     * 수강이력 조회 (연도, 학기 정보 포함)
     * @param loggedInUser 로그인 유저
     * @return 로그인 유저의 수강이력 목록
     */
    @GetMapping("/history")
    public ResultForm getCourseList(@LoginUser Users loggedInUser) {
        return ResultFormUtils.getSuccessResultForm(enrollmentService.getCourseList(loggedInUser));
    }
}