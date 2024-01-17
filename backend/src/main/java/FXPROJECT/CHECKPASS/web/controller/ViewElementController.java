package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.enums.CollegesEnum;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaCollegesRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/viewElement")
public class ViewElementController {

    private final JpaDepartmentRepository jpaDepartmentRepository;
    private final JpaCollegesRepository jpaCollegesRepository;

    @GetMapping("/colleges")
    public ResultForm colleges(){

        CollegesEnum[] values = CollegesEnum.values();

        List<String> collegeNames = new ArrayList<>();

        for (CollegesEnum collegesEnum :values) {
            collegeNames.add(collegesEnum.getCollege());
        }

        return ResultFormUtils.getSuccessResultForm(collegeNames);
    }

    @GetMapping("/departments/{collegeName}")
    public ResultForm departments(@PathVariable("collegeName") String collegeName){

        try {

            Long collegeByCollegeName = jpaCollegesRepository.findCollegeByCollegeName(collegeName);

            DepartmentsEnum[] departmentsEnums = DepartmentsEnum.values();

            List<String> targetDepartments = new ArrayList<>();

            for (DepartmentsEnum departmentsEnum : departmentsEnums) {
                if(departmentsEnum.getCollegeCode() == collegeByCollegeName){
                    targetDepartments.add(departmentsEnum.getDepartment());
                }
            }

            Map<String,List<String>> departments = new HashMap<>();
            departments.put(collegeName,targetDepartments);

            return ResultFormUtils.getSuccessResultForm(departments);


        }catch (Exception e){
            return ResultFormUtils.getFailResultForm(MISSING_REQUIRED_ARGUMENT.getCode(), MISSING_REQUIRED_ARGUMENT.getTitle(), MISSING_REQUIRED_ARGUMENT.getDescription(), e);
        }



    }


}
