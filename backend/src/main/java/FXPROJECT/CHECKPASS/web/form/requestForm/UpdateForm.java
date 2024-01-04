package FXPROJECT.CHECKPASS.web.form.requestForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateForm {

    private Long updateId;
    private String updatePassword;
    private String updateName;
    private String updateCollege;
    private String updateDepartment;

}
