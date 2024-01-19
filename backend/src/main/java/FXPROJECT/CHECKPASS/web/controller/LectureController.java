package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.ExistingLecture;
import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.common.exception.NonExistingLecture;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureRegisterForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update.LectureUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/registerLecture")
    public ResultForm registerLecture(@RequestBody @Validated LectureRegisterForm form, BindingResult bindingResult){
        Lecture lecture = lectureService.transferToLecture(form);

        if(!lectureService.registerLecture(lecture)){
            throw new ExistingLecture();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_REGISTER.getDescription());

    }

    @PatchMapping("/{lectureCode}")
    public ResultForm editLectureInformation(@PathVariable("lectureCode") Long lectureCode, @RequestBody LectureUpdateForm form, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Users loggedInUser = (Users) session.getAttribute("loginMember");

        if (lectureService.checkPermission(loggedInUser, Job.STUDENTS)){
            log.info("WARNING : 학생이 강의 수정 API를 요청했습니다. 접근한 유저 : {}", loggedInUser.getUserId());
            throw new NoPermission();
        }

        if (!lectureService.existsLecture(lectureCode)){
            throw new NonExistingLecture();
        }

        Lecture target = lectureService.getLecture(lectureCode);

        if (!lectureService.checkPermission(loggedInUser.getDepartments(), target.getDepartments())) {
            throw new NoPermission();
        }

        if (lectureService.checkPermission(loggedInUser, Job.PROFESSOR) && !lectureService.checkPermission(loggedInUser, target.getProfessor())) {
            throw new NoPermission();
        }

        lectureService.editLectureInformation(target, form);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_UPDATE.getDescription());
    }

    @DeleteMapping("/{lectureCode}")
    public ResultForm deleteLecture(@PathVariable("lectureCode") Long lectureCode){
        return lectureService.deleteLecture(lectureCode);
    }

}
