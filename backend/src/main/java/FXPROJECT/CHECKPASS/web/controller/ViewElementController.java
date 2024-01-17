package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.enums.CollegesEnum;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/viewElement")
public class ViewElementController {


    @GetMapping("/colleges")
    public ResultForm colleges(){

        CollegesEnum[] values = CollegesEnum.values();

        List<String> collegeNames = new ArrayList<>();

        for (CollegesEnum collegesEnum :values) {
            collegeNames.add(collegesEnum.getCollege());
        }

        return ResultFormUtils.getSuccessResultForm(collegeNames);
    }


}
