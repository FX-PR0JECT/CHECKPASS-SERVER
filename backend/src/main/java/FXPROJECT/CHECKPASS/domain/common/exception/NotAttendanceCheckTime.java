package FXPROJECT.CHECKPASS.domain.common.exception;

public class NotAttendanceCheckTime extends RuntimeException{
    public NotAttendanceCheckTime() {
    }

    public NotAttendanceCheckTime(String message) {
        super(message);
    }

    public NotAttendanceCheckTime(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAttendanceCheckTime(Throwable cause) {
        super(cause);
    }

    public NotAttendanceCheckTime(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
