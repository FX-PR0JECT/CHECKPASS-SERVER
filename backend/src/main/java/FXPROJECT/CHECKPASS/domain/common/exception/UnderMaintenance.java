package FXPROJECT.CHECKPASS.domain.common.exception;

public class UnderMaintenance extends RuntimeException{
    public UnderMaintenance() {
    }

    public UnderMaintenance(String message) {
        super(message);
    }

    public UnderMaintenance(String message, Throwable cause) {
        super(message, cause);
    }

    public UnderMaintenance(Throwable cause) {
        super(cause);
    }

    public UnderMaintenance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
