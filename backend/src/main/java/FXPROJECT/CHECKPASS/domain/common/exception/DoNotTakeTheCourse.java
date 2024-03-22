package FXPROJECT.CHECKPASS.domain.common.exception;

public class DoNotTakeTheCourse extends RuntimeException {
    public DoNotTakeTheCourse() {
    }

    public DoNotTakeTheCourse(String message) {
        super(message);
    }

    public DoNotTakeTheCourse(String message, Throwable cause) {
        super(message, cause);
    }

    public DoNotTakeTheCourse(Throwable cause) {
        super(cause);
    }

    public DoNotTakeTheCourse(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
