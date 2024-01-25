package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
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

    @PostMapping("/{lectureCode}")
    public ResultForm enrollment(@PathVariable("lectureCode") Long lectureCode, @LoginUser Users loggedInUser){

        if (loggedInUser.getUserJob() != Job.STUDENTS) {
            throw new NoPermission();
        }
        return enrollmentService.enrollment(lectureCode, loggedInUser);
    }

    @DeleteMapping("{lectureCode}")
    public ResultForm cancelEnrollment(@PathVariable("lectureCode") Long lectureCode, @LoginUser Users loggedInUser){

        if (loggedInUser.getUserJob() != Job.STUDENTS) {
            throw new NoPermission();
        }
        return enrollmentService.cancelEnrollment(lectureCode, loggedInUser);
    }
}