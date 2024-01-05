package FXPROJECT.CHECKPASS.domain.common.exception;

public class SearchFail extends RuntimeException{
    public SearchFail() {
    }

    public SearchFail(String message) {
        super(message);
    }

    public SearchFail(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchFail(Throwable cause) {
        super(cause);
    }

    public SearchFail(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
