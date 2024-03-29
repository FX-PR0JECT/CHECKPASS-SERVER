package FXPROJECT.CHECKPASS.domain.common.exception;

public class ExistingBeacon extends RuntimeException{

    public ExistingBeacon() {
    }

    public ExistingBeacon(String message) {
        super(message);
    }

    public ExistingBeacon(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingBeacon(Throwable cause) { super(cause); }

    public ExistingBeacon(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}