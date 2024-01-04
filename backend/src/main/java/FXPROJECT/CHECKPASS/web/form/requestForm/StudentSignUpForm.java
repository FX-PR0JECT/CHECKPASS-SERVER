package FXPROJECT.CHECKPASS.web.form.requestForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSignUpForm extends SignUpForm{

    private String signUpGrade;

    private String signUpDayOrNight;

    private String signUpSemester;

}
