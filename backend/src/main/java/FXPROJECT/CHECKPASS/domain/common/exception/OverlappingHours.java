package FXPROJECT.CHECKPASS.domain.common.exception;

public class OverlappingHours extends RuntimeException{

    public OverlappingHours() {
    }

    public OverlappingHours (String message) {
        super(message);
    }

    public OverlappingHours(String message, Throwable cause) {
        super(message, cause);
    }

    public OverlappingHours (Throwable cause) {
        super(cause);
    }

    public OverlappingHours(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
