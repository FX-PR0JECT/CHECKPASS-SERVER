package FXPROJECT.CHECKPASS.domain.common.exception.advice;

import FXPROJECT.CHECKPASS.domain.common.exception.DupleUsers;
import FXPROJECT.CHECKPASS.web.form.resultForm.ResultForm;
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
                .state(HttpStatus.BAD_REQUEST.toString())
                .code("SU0001")
                .resultSet("이미 존재하는 회원입니다.")
                .build();
    }
}
