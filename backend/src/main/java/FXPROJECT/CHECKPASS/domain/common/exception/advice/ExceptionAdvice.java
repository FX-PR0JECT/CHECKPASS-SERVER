package FXPROJECT.CHECKPASS.domain.common.exception.advice;

import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.exception.*;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServerMaintenance.class)
    public ResultForm serverMaintenance(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.SERVER_MAINTENANCE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidHeader.class)
    public ResultForm invalidHeader(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.INVALID_HEADER);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceTerminated.class)
    public ResultForm serviceTerminated(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.SERVICE_TERMINATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestCountExceeded.class)
    public ResultForm requestCountExceeded(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.REQUEST_COUNT_EXCEEDED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InformationDiscrepancy.class)
    public ResultForm informationDiscrepancy(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.INFORMATION_DISCREPANCY);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageCapacityExceeded.class)
    public ResultForm imageCapacityExceeded(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.IMAGE_CAPACITY_EXCEEDED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TimeOut.class)
    public ResultForm timeOut(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.TIME_OUT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberOfImagesExceeded.class)
    public ResultForm numberOfImagesExceeded(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.NUMBER_OF_IMAGES_EXCEEDED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageFormatError.class)
    public ResultForm imageFormatError(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.IMAGE_FORMAT_ERROR);
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(UnderMaintenance.class)
    public ResultForm underMaintenance(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.UNDER_MAINTENANCE);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SanctionsUser.class)
    public ResultForm sanctionsUser(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.SANCTIONS_USER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadUriRequest.class)
    public ResultForm badUriRequest(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.BAD_URI_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequiredArgument.class)
    public ResultForm missingRequiredArgument(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.MISSING_REQUIRED_ELEMENT);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerError.class)
    public ResultForm serverError(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnknownServerError.class)
    public ResultForm unknownServerError(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.UNKNOWN_SERVER_ERROR);
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MissingRequiredElement.class)
    public ResultForm missionRequiredElement(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.EXISTING_USER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingUSER.class)
    public ResultForm existingUser(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.EXISTING_USER);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthenticatedUser.class)
    public ResultForm unauthenticatedUser(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.UNAUTHENTICATED_USER);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalException.class)
    public ResultForm InternalException(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRoleRequest.class)
    public ResultForm InvalidRoleRequest(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.INVALID_ROLE_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSearchResultsFound.class)
    public ResultForm noSearchResultsFound(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.NO_SEARCH_RESULTS_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoPermission.class)
    public ResultForm noPermission(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.NO_PERMISSION);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingLecture.class)
    public ResultForm existingLecture(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.EXISTING_LECTURE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NonExistingLecture.class)
    public ResultForm nonExistingLecture(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.NON_EXISTING_LECTURE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultForm notReadable(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.MISSING_REQUIRED_ARGUMENT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberOfStudentsExceeded.class)
    public ResultForm numberOfStudentsExceeded(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.NUMBER_OF_STUDENTS_EXCEEDED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OverlappingHours.class)
    public ResultForm overlappingHours(Exception e){
        return ResultFormUtils.getFailResultForm(ErrorCode.OVERLAPPING_HOURS);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegisteredForLecture.class)
    public ResultForm registeredForLecture(Exception e) {
        return ResultFormUtils.getFailResultForm(ErrorCode.OVERLAPPING_HOURS);
    }

}
