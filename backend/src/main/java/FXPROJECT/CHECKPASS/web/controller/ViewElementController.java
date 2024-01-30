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

        Map<String,String> collegeNames = new HashMap<>();

        for (CollegesEnum collegesEnum :values) {
            collegeNames.put(collegesEnum.getCollege(),collegesEnum.name());
        }

        return ResultFormUtils.getSuccessResultForm(collegeNames);
    }

    @GetMapping("/departments/{collegeName}")
    public ResultForm departments(@PathVariable("collegeName") String collegeName){

        try {

            Long collegeByCollegeName = jpaCollegesRepository.findCollegeByCollegeName(collegeName);

            DepartmentsEnum[] departmentsEnums = DepartmentsEnum.values();

            List<List<String>> targetDepartments = new ArrayList<>();

            for (DepartmentsEnum departmentsEnum : departmentsEnums) {
                if(departmentsEnum.getCollegeCode() == collegeByCollegeName){
                    List<String> oneDepartment = new ArrayList<>();
                    oneDepartment.add(departmentsEnum.getDepartment());
                    oneDepartment.add(departmentsEnum.name());
                    targetDepartments.add(oneDepartment);
                }
            }

            Map<String,List<List<String>>> departments = new HashMap<>();
            departments.put(collegeName,targetDepartments);

            return ResultFormUtils.getSuccessResultForm(departments);


        }catch (Exception e){
            return ResultFormUtils.getFailResultForm(MISSING_REQUIRED_ARGUMENT);
        }



    }


}
