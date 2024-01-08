package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResultSetUtils {

    public static ResultForm getResultForm(String state, Integer code, String title, String description, Exception e) {
        log.error("[exceptionHandle] ex" , e);
        return new ResultForm().builder()
                .state(state)
                .code(code)
                .title(title)
                .resultSet(description)
                .build();
    }

    public static ResultForm getResultForm(String state, Integer code, String title, Object description, Exception e) {
        log.error("[exceptionHandle] ex" , e);
        return new ResultForm().builder()
                .state(state)
                .code(code)
                .title(title)
                .resultSet(description)
                .build();
    }

}
