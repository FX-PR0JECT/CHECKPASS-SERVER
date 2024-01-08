package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoPermission extends RuntimeException{

    public NoPermission() {
    }

    public NoPermission(String message) {
        super(message);
    }

    public NoPermission(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermission(Throwable cause) {
        super(cause);
    }

    public NoPermission(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
