package FXPROJECT.CHECKPASS.web.form.requestForm.users.signup;

import FXPROJECT.CHECKPASS.domain.enums.Job;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {

    protected Long signUpId;

    protected String signUpPassword;

    protected String signUpName;

    protected Job signUpJob;

    protected String signUpCollege;

    protected String signUpDepartment;

}
