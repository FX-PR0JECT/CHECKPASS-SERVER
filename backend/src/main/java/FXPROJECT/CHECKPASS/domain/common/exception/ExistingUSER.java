package FXPROJECT.CHECKPASS.domain.common.exception;

public class ExistingUSER extends RuntimeException{

    public ExistingUSER() {
    }

    public ExistingUSER(String message) {
        super(message);
    }

    public ExistingUSER(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingUSER(Throwable cause) {
        super(cause);
    }

    public ExistingUSER(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
