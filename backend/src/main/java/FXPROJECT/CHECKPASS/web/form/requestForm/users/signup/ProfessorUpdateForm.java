package FXPROJECT.CHECKPASS.web.form.requestForm.users.signup;


import FXPROJECT.CHECKPASS.web.form.requestForm.users.update.UpdateForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorUpdateForm extends UpdateForm {

    private String updateHireDate;

}
