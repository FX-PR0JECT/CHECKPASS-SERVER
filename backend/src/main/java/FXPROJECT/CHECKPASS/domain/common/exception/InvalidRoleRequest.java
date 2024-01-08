package FXPROJECT.CHECKPASS.domain.common.exception;

public class InvalidRoleRequest extends RuntimeException{

    public InvalidRoleRequest() {
    }

    public InvalidRoleRequest(String message) {
        super(message);
    }

    public InvalidRoleRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRoleRequest(Throwable cause) {
        super(cause);
    }

    public InvalidRoleRequest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
