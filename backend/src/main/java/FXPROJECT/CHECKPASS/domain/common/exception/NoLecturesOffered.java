package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoLecturesOffered extends RuntimeException {
    public NoLecturesOffered() {
    }

    public NoLecturesOffered(String message) {
        super(message);
    }

    public NoLecturesOffered(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLecturesOffered(Throwable cause) { super(cause); }

    public NoLecturesOffered(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
