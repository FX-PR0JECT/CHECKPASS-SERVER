package FXPROJECT.CHECKPASS.domain.common.exception;

public class MissingRequiredArgument extends RuntimeException{
    public MissingRequiredArgument() {
    }

    public MissingRequiredArgument(String message) {
        super(message);
    }

    public MissingRequiredArgument(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingRequiredArgument(Throwable cause) {
        super(cause);
    }

    public MissingRequiredArgument(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
