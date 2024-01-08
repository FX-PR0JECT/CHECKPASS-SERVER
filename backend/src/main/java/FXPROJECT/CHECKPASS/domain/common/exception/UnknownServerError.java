package FXPROJECT.CHECKPASS.domain.common.exception;

public class UnknownServerError extends RuntimeException{
    public UnknownServerError() {
    }

    public UnknownServerError(String message) {
        super(message);
    }

    public UnknownServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownServerError(Throwable cause) {
        super(cause);
    }

    public UnknownServerError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
