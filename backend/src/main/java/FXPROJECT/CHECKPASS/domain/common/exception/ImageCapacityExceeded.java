package FXPROJECT.CHECKPASS.domain.common.exception;

public class ImageCapacityExceeded extends RuntimeException{
    public ImageCapacityExceeded() {
    }

    public ImageCapacityExceeded(String message) {
        super(message);
    }

    public ImageCapacityExceeded(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageCapacityExceeded(Throwable cause) {
        super(cause);
    }

    public ImageCapacityExceeded(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
