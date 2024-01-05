package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoAuthentication extends RuntimeException{

    public NoAuthentication() {
    }

    public NoAuthentication(String message) {
        super(message);
    }

    public NoAuthentication(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthentication(Throwable cause) {
        super(cause);
    }

    public NoAuthentication(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
