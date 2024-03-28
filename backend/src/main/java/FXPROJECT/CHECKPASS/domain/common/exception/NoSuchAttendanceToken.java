package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoSuchAttendanceToken extends RuntimeException{

    public NoSuchAttendanceToken() {
    }

    public NoSuchAttendanceToken(String message) {
        super(message);
    }

    public NoSuchAttendanceToken(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchAttendanceToken(Throwable cause) {
        super(cause);
    }

    public NoSuchAttendanceToken(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
