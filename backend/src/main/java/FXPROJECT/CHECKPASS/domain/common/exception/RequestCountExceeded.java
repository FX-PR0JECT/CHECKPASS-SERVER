package FXPROJECT.CHECKPASS.domain.common.exception;

public class RequestCountExceeded extends RuntimeException{
    public RequestCountExceeded() {
    }

    public RequestCountExceeded(String message) {
        super(message);
    }

    public RequestCountExceeded(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestCountExceeded(Throwable cause) {
        super(cause);
    }

    public RequestCountExceeded(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
