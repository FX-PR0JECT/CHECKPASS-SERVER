package FXPROJECT.CHECKPASS.domain.common.exception;

public class TimeOut extends RuntimeException{
    public TimeOut() {
    }

    public TimeOut(String message) {
        super(message);
    }

    public TimeOut(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeOut(Throwable cause) {
        super(cause);
    }

    public TimeOut(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
