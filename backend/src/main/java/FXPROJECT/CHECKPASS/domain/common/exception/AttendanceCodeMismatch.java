package FXPROJECT.CHECKPASS.domain.common.exception;

public class AttendanceCodeMismatch extends RuntimeException{
    public AttendanceCodeMismatch() {
    }

    public AttendanceCodeMismatch(String message) {
        super(message);
    }

    public AttendanceCodeMismatch(String message, Throwable cause) {
        super(message, cause);
    }

    public AttendanceCodeMismatch(Throwable cause) {
        super(cause);
    }

    public AttendanceCodeMismatch(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
