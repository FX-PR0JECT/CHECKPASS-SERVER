package FXPROJECT.CHECKPASS.domain.common.exception;

public class NonExistentLecture extends RuntimeException{

    public NonExistentLecture() {
    }

    public NonExistentLecture(String message) {
        super(message);
    }

    public NonExistentLecture(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentLecture(Throwable cause) {
        super(cause);
    }

    public NonExistentLecture(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}