package FXPROJECT.CHECKPASS.domain.common.exception;

public class ImageFormatError extends RuntimeException{
    public ImageFormatError() {
    }

    public ImageFormatError(String message) {
        super(message);
    }

    public ImageFormatError(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageFormatError(Throwable cause) {
        super(cause);
    }

    public ImageFormatError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
