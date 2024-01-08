package FXPROJECT.CHECKPASS.domain.common.exception;

public class ServiceTerminated extends RuntimeException{
    public ServiceTerminated() {
    }

    public ServiceTerminated(String message) {
        super(message);
    }

    public ServiceTerminated(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceTerminated(Throwable cause) {
        super(cause);
    }

    public ServiceTerminated(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
