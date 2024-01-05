package FXPROJECT.CHECKPASS.domain.common.exception.advice;

import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.domain.common.exception.*;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DupleUsers.class)
    public ResultForm exceptionModel(Exception e){
        log.error("[exceptionHandle] ex" , e);
        return new ResultForm().builder()
                .state(State.FAIL)
                .code(ErrorCode.DUPLICATION_USERS.getCode())
                .resultSet(ErrorCode.DUPLICATION_USERS.getDescription())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchUser.class)
    public ResultForm noSuchUserException(Exception e){
        log.error("[exception] ex" , e);
        return new ResultForm().builder()
                .state(State.FAIL)
                .code(ErrorCode.NO_SUCH_USER.getCode())
                .resultSet(ErrorCode.NO_SUCH_USER.getDescription())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalException.class)
    public ResultForm InternalException(Exception e){
        log.error("[exception] ex",e);
        return new ResultForm().builder()
                .state(State.FAIL)
                .code(ErrorCode.INTERNAL_ERROR.getCode())
                .resultSet(ErrorCode.INTERNAL_ERROR.getDescription())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequest.class)
    public ResultForm InvalidRequest(Exception e){
        log.info("[exception] ex",e);
        return new ResultForm().builder()
                .state(State.FAIL)
                .code(ErrorCode.INVALID_REQUEST.getCode())
                .resultSet(ErrorCode.INVALID_REQUEST.getDescription())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SearchFail.class)
    public ResultForm searchFail(Exception e){
        log.info("[exception] ex", e);
        return new ResultForm().builder()
                .state(State.FAIL)
                .code(ErrorCode.SERCH_FAIL.getCode())
                .resultSet(ErrorCode.SERCH_FAIL.getDescription())
                .build();
    }

}
