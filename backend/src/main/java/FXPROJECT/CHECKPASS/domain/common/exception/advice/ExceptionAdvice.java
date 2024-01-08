package FXPROJECT.CHECKPASS.domain.common.exception.advice;

import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.exception.*;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static FXPROJECT.CHECKPASS.domain.common.constant.State.*;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    private static ResultForm getResultForm(String state, Integer code, String title, String description,Exception e) {
        log.error("[exceptionHandle] ex" , e);
        return new ResultForm().builder()
                .state(state)
                .code(code)
                .title(title)
                .resultSet(description)
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServerMaintenance.class)
    public ResultForm serverMaintenance(Exception e){
        return getResultForm(FAIL,
                ErrorCode.SERVER_MAINTENANCE.getCode(),
                ErrorCode.SERVER_MAINTENANCE.getTitle(),
                ErrorCode.SERVER_MAINTENANCE.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidHeader.class)
    public ResultForm invalidHeader(Exception e){
        return getResultForm(FAIL,
                ErrorCode.INVALID_HEADER.getCode(),
                ErrorCode.INVALID_HEADER.getTitle(),
                ErrorCode.INVALID_HEADER.getDescription(),
                e);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceTerminated.class)
    public ResultForm serviceTerminated(Exception e){
        return getResultForm(FAIL,
                ErrorCode.SERVICE_TERMINATED.getCode(),
                ErrorCode.SERVICE_TERMINATED.getTitle(),
                ErrorCode.SERVICE_TERMINATED.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestCountExceeded.class)
    public ResultForm requestCountExceeded(Exception e){
        return getResultForm(FAIL,
                ErrorCode.REQUEST_COUNT_EXCEEDED.getCode(),
                ErrorCode.REQUEST_COUNT_EXCEEDED.getTitle(),
                ErrorCode.REQUEST_COUNT_EXCEEDED.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RequestCountExceeded.class)
    public ResultForm informationDiscrepancy(Exception e){
        return getResultForm(FAIL,
                ErrorCode.INFORMATION_DISCREPANCY.getCode(),
                ErrorCode.INFORMATION_DISCREPANCY.getTitle(),
                ErrorCode.INFORMATION_DISCREPANCY.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageCapacityExceeded.class)
    public ResultForm imageCapacityExceeded(Exception e){
        return getResultForm(FAIL,
                ErrorCode.IMAGE_CAPACITY_EXCEEDED.getCode(),
                ErrorCode.IMAGE_CAPACITY_EXCEEDED.getTitle(),
                ErrorCode.IMAGE_CAPACITY_EXCEEDED.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TimeOut.class)
    public ResultForm timeOut(Exception e){
        return getResultForm(FAIL,
                ErrorCode.TIME_OUT.getCode(),
                ErrorCode.TIME_OUT.getTitle(),
                ErrorCode.TIME_OUT.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberOfImagesExceeded.class)
    public ResultForm numberOfImagesExceeded(Exception e){
        return getResultForm(FAIL,
                ErrorCode.NUMBER_OF_IMAGES_EXCEEDED.getCode(),
                ErrorCode.NUMBER_OF_IMAGES_EXCEEDED.getTitle(),
                ErrorCode.NUMBER_OF_IMAGES_EXCEEDED.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageFormatError.class)
    public ResultForm imageFormatError(Exception e){
        return getResultForm(FAIL,
                ErrorCode.IMAGE_FORMAT_ERROR.getCode(),
                ErrorCode.IMAGE_FORMAT_ERROR.getTitle(),
                ErrorCode.IMAGE_FORMAT_ERROR.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(UnderMaintenance.class)
    public ResultForm underMaintenance(Exception e){
        return getResultForm(FAIL,
                ErrorCode.UNDER_MAINTENANCE.getCode(),
                ErrorCode.UNDER_MAINTENANCE.getTitle(),
                ErrorCode.UNDER_MAINTENANCE.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SanctionsUser.class)
    public ResultForm sanctionsUser(Exception e){
        return getResultForm(FAIL,
                ErrorCode.SANCTIONS_USER.getCode(),
                ErrorCode.SANCTIONS_USER.getTitle(),
                ErrorCode.SANCTIONS_USER.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadUriRequest.class)
    public ResultForm badUriRequest(Exception e){
        return getResultForm(FAIL,
                ErrorCode.BAD_URI_REQUEST.getCode(),
                ErrorCode.BAD_URI_REQUEST.getTitle(),
                ErrorCode.BAD_URI_REQUEST.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequiredArgument.class)
    public ResultForm missingRequiredArgument(Exception e){
        return getResultForm(FAIL,
                ErrorCode.MISSING_REQUIRED_ELEMENT.getCode(),
                ErrorCode.MISSING_REQUIRED_ELEMENT.getTitle(),
                ErrorCode.MISSING_REQUIRED_ELEMENT.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerError.class)
    public ResultForm serverError(Exception e){
        return getResultForm(FAIL,
                ErrorCode.SERVER_ERROR.getCode(),
                ErrorCode.SERVER_ERROR.getTitle(),
                ErrorCode.SERVER_ERROR.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnknownServerError.class)
    public ResultForm unknownServerError(Exception e){
        return getResultForm(FAIL,
                ErrorCode.UNKNOWN_SERVER_ERROR.getCode(),
                ErrorCode.UNKNOWN_SERVER_ERROR.getTitle(),
                ErrorCode.UNKNOWN_SERVER_ERROR.getDescription(),
                e);
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MissingRequiredElement.class)
    public ResultForm missionRequiredElement(Exception e){
        return getResultForm(FAIL,
                ErrorCode.EXISTING_USER.getCode(),
                ErrorCode.EXISTING_USER.getTitle(),
                ErrorCode.EXISTING_USER.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingUSER.class)
    public ResultForm existingUser(Exception e){
        return getResultForm(FAIL,
                ErrorCode.EXISTING_USER.getCode(),
                ErrorCode.EXISTING_USER.getTitle(),
                ErrorCode.EXISTING_USER.getDescription(),
                e);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthenticatedUser.class)
    public ResultForm unauthenticatedUser(Exception e){
        return getResultForm(FAIL,
                ErrorCode.UNAUTHENTICATED_USER.getCode(),
                ErrorCode.UNAUTHENTICATED_USER.getTitle(),
                ErrorCode.UNAUTHENTICATED_USER.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalException.class)
    public ResultForm InternalException(Exception e){
        return getResultForm(FAIL,
                ErrorCode.SERVER_ERROR.getCode(),
                ErrorCode.SERVER_ERROR.getTitle(),
                ErrorCode.SERVER_ERROR.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRoleRequest.class)
    public ResultForm InvalidRoleRequest(Exception e){
        return getResultForm(FAIL,
                ErrorCode.INVALID_ROLE_REQUEST.getCode(),
                ErrorCode.INVALID_ROLE_REQUEST.getTitle(),
                ErrorCode.INVALID_ROLE_REQUEST.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSearchResultsFound.class)
    public ResultForm noSearchResultsFound(Exception e){
        return getResultForm(FAIL,
                ErrorCode.NO_SEARCH_RESULTS_FOUND.getCode(),
                ErrorCode.NO_SEARCH_RESULTS_FOUND.getTitle(),
                ErrorCode.NO_SEARCH_RESULTS_FOUND.getDescription(),
                e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoPermission.class)
    public ResultForm noPermission(Exception e){
        return getResultForm(FAIL,
                ErrorCode.NO_PERMISSION.getCode(),
                ErrorCode.NO_PERMISSION.getTitle(),
                ErrorCode.NO_PERMISSION.getDescription(),
                e);
    }

}
