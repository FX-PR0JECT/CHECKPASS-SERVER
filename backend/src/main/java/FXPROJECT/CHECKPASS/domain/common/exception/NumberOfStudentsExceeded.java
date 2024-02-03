package FXPROJECT.CHECKPASS.domain.common.exception;

public class NumberOfStudentsExceeded extends RuntimeException{
    public NumberOfStudentsExceeded() {
    }

    public NumberOfStudentsExceeded(String message) {
        super(message);
    }

    public NumberOfStudentsExceeded(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfStudentsExceeded(Throwable cause) {
        super(cause);
    }

    public NumberOfStudentsExceeded(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
