package FXPROJECT.CHECKPASS.web.form.responseForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserInformation {

    private Long userId;

    private String userName;

    private String userDepartment;

}
