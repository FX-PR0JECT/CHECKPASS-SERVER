package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage;
import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.State.*;

@Slf4j
@Component
public class ResultFormUtils {

    public static ResultForm getFailResultForm(Integer code, String title, String description,Exception e) {
        log.info("[ exception : ]" , e);
        return new ResultForm().builder()
                .state(FAIL)
                .code(code)
                .title(title)
                .resultSet(description)
                .build();
    }

    public static ResultForm getSuccessResultForm(Object resultSet) {
        return new ResultForm().builder()
                .state(SUCCESS)
                .code(OK.getCode())
                .title(OK.getTitle())
                .resultSet(resultSet)
                .build();
    }

}
