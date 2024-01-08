package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoSearchResultsFound extends RuntimeException{
    public NoSearchResultsFound() {
    }

    public NoSearchResultsFound(String message) {
        super(message);
    }

    public NoSearchResultsFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSearchResultsFound(Throwable cause) {
        super(cause);
    }

    public NoSearchResultsFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
