package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.ExistingLecture;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureRegisterForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update.LectureUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
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
    public ResultForm editLectureInformation(@PathVariable("lectureCode") Long lectureCode, @RequestBody LectureUpdateForm form) {

        Lecture lecture = lectureService.editLectureInformation(lectureCode, form);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_UPDATE.getDescription());
    }

    @DeleteMapping("/{lectureCode}")
    public ResultForm deleteLecture(@PathVariable("lectureCode") Long lectureCode){
        return lectureService.deleteLecture(lectureCode);
    }

}
