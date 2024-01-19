package FXPROJECT.CHECKPASS.domain.common.exception;

public class NonExistingLecture extends RuntimeException{

    public NonExistingLecture() {
    }

    public NonExistingLecture(String message) {
        super(message);
    }

    public NonExistingLecture(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingLecture(Throwable cause) {
        super(cause);
    }

    public NonExistingLecture(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
