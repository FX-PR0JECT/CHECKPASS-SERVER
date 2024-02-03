package FXPROJECT.CHECKPASS.domain.common.exception;

public class RegisteredForLecture extends RuntimeException{

    public RegisteredForLecture() {
    }

    public RegisteredForLecture(String message) {
        super(message);
    }

    public RegisteredForLecture(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisteredForLecture(Throwable cause) {
        super(cause);
    }

    public RegisteredForLecture(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
