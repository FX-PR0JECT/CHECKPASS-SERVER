package FXPROJECT.CHECKPASS.web.form.responseForm.resultForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResultForm {

    private String state;
    private Integer code;
    private String title;
    private Object resultSet;

    public ResultForm() {
    }
}
