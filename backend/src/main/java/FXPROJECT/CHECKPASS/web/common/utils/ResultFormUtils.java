package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.State.*;

@Slf4j
@Component
public class ResultFormUtils {

    public static ResultForm getSuccessResultForm(Object resultSet) {
        return new ResultForm().builder()
                .state(SUCCESS)
                .code(OK.getCode())
                .title(OK.getTitle())
                .resultSet(resultSet)
                .build();
    }

    public static ResultForm getFailResultForm(ErrorCode errorCode) {
        return new ResultForm().builder()
                .state(FAIL)
                .code(errorCode.getCode())
                .title(errorCode.getTitle())
                .resultSet(errorCode.getDescription())
                .build();
    }

    public static ResultForm getFailResultForm(ErrorCode errorCode, String message) {
        return new ResultForm().builder()
                .state(FAIL)
                .code(errorCode.getCode())
                .title(errorCode.getTitle())
                .resultSet(message)
                .build();
    }
}
