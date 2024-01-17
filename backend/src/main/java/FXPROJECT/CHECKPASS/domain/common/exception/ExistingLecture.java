package FXPROJECT.CHECKPASS.domain.common.exception;

public class ExistingLecture extends RuntimeException{

    public ExistingLecture() {
    }

    public ExistingLecture(String message) {
        super(message);
    }

    public ExistingLecture(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingLecture(Throwable cause) {
        super(cause);
    }

    public ExistingLecture(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
