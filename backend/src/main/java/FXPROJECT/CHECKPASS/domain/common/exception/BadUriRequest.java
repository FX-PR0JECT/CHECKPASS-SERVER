package FXPROJECT.CHECKPASS.domain.common.exception;

public class BadUriRequest extends RuntimeException{
    public BadUriRequest() {
    }

    public BadUriRequest(String message) {
        super(message);
    }

    public BadUriRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public BadUriRequest(Throwable cause) {
        super(cause);
    }

    public BadUriRequest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
