package FXPROJECT.CHECKPASS.domain.common.exception;

public class InvalidHeader extends RuntimeException{
    public InvalidHeader() {
    }

    public InvalidHeader(String message) {
        super(message);
    }

    public InvalidHeader(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidHeader(Throwable cause) {
        super(cause);
    }

    public InvalidHeader(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
