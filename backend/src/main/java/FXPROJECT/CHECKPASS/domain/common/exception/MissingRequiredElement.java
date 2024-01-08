package FXPROJECT.CHECKPASS.domain.common.exception;


public class MissingRequiredElement extends RuntimeException{

    public MissingRequiredElement() {
    }

    public MissingRequiredElement(String message) {
        super(message);
    }

    public MissingRequiredElement(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingRequiredElement(Throwable cause) {
        super(cause);
    }

    public MissingRequiredElement(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
