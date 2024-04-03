package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoCoursesMatchedToBeacon extends RuntimeException {
    public NoCoursesMatchedToBeacon() {
    }

    public NoCoursesMatchedToBeacon(String message) {
        super(message);
    }

    public NoCoursesMatchedToBeacon(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCoursesMatchedToBeacon(Throwable cause) { super(cause); }

    public NoCoursesMatchedToBeacon(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
