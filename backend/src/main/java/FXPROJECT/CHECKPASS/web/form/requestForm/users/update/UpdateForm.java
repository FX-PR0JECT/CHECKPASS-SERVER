package FXPROJECT.CHECKPASS.web.form.requestForm.users.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateForm {

    private String updateName;

    private String updateDepartment;

}
