package FXPROJECT.CHECKPASS.web.form.responseForm.resultForm;

import FXPROJECT.CHECKPASS.domain.enums.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserForm {

    private Long userId;

    private String userName;

    private Job userJob;

}
