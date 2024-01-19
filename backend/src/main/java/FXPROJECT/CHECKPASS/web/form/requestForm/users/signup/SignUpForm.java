package FXPROJECT.CHECKPASS.web.form.requestForm.users.signup;

import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import FXPROJECT.CHECKPASS.domain.enums.CollegesEnum;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {

    protected Long signUpId;

    protected String signUpPassword;

    protected String signUpName;

    protected Job signUpJob;

    protected CollegesEnum signUpCollege;

    protected DepartmentsEnum signUpDepartment;

}
