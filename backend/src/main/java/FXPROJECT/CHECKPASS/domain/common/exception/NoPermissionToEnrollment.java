package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoPermissionToEnrollment extends RuntimeException{

    public NoPermissionToEnrollment() {
    }

    public NoPermissionToEnrollment(String message) {
        super(message);
    }

    public NoPermissionToEnrollment(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionToEnrollment(Throwable cause) {
        super(cause);
    }

    public NoPermissionToEnrollment(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}