package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage;
import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.web.common.utils.ResultSetUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.letures.register.LectureRegisterForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.State.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;

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
            return ResultSetUtils.getResultForm(FAIL, EXISTING_LECTURE.getCode(), EXISTING_LECTURE.getTitle(), FAIL_REGISTER.getDescription(),null);
        }

        return ResultSetUtils.getResultForm(SUCCESS, OK.getCode(), OK.getTitle(), COMPLETE_REGISTER.getDescription(), null);

    }

    @DeleteMapping("/{lectureCode}")
    public ResultForm deleteLecture(@PathVariable("lectureCode") Long lectureCode){
        return lectureService.deleteLecture(lectureCode);
    }

}
