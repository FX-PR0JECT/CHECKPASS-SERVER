package FXPROJECT.CHECKPASS.domain.common.exception;

public class AttendanceAlreadyProcessed extends RuntimeException {
    public AttendanceAlreadyProcessed() {
    }

    public AttendanceAlreadyProcessed(String message) {
        super(message);
    }

    public AttendanceAlreadyProcessed(String message, Throwable cause) {
        super(message, cause);
    }

    public AttendanceAlreadyProcessed(Throwable cause) {
        super(cause);
    }

    public AttendanceAlreadyProcessed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
