package FXPROJECT.CHECKPASS.domain.common.exception;

public class UnauthenticatedUser extends RuntimeException{

    public UnauthenticatedUser() {
    }

    public UnauthenticatedUser(String message) {
        super(message);
    }

    public UnauthenticatedUser(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedUser(Throwable cause) {
        super(cause);
    }

    public UnauthenticatedUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
