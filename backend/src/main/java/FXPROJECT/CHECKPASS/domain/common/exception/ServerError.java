package FXPROJECT.CHECKPASS.domain.common.exception;

public class ServerError extends RuntimeException{
    public ServerError() {
    }

    public ServerError(String message) {
        super(message);
    }

    public ServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerError(Throwable cause) {
        super(cause);
    }

    public ServerError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
