package FXPROJECT.CHECKPASS.domain.common.exception;

public class NumberOfImagesExceeded extends RuntimeException{
    public NumberOfImagesExceeded() {
    }

    public NumberOfImagesExceeded(String message) {
        super(message);
    }

    public NumberOfImagesExceeded(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfImagesExceeded(Throwable cause) {
        super(cause);
    }

    public NumberOfImagesExceeded(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
