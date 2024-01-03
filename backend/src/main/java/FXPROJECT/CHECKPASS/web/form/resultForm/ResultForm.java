package FXPROJECT.CHECKPASS.web.form.resultForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResultForm {

    private String state;
    private String code;
    private Object resultSet;

    public ResultForm() {
    }
}
