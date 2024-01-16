package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @DeleteMapping("/{lectureCode}")
    public ResultForm deleteLecture(@PathVariable("lectureCode") Long lectureCode){
        return lectureService.deleteLecture(lectureCode);
    }

}
