package FXPROJECT.CHECKPASS.domain.common.exception;

public class InvalidRequest extends RuntimeException{

    public InvalidRequest() {
    }

    public InvalidRequest(String message) {
        super(message);
    }

    public InvalidRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequest(Throwable cause) {
        super(cause);
    }

    public InvalidRequest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
