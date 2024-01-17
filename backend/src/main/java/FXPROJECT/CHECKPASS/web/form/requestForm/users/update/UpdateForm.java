package FXPROJECT.CHECKPASS.web.form.requestForm.users.update;

import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateForm {

    private String updatePassword;

    private String updateName;

    private String updateDepartment;

}
