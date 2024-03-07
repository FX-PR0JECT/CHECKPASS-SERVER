package FXPROJECT.CHECKPASS.domain.common.exception;

public class NonExistentBeacon extends RuntimeException {

    public NonExistentBeacon() {
    }

    public NonExistentBeacon(String message) {
        super(message);
    }

    public NonExistentBeacon(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentBeacon(Throwable cause) { super(cause); }

    public NonExistentBeacon(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}