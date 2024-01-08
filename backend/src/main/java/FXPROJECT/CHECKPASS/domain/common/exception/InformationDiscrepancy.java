package FXPROJECT.CHECKPASS.domain.common.exception;

public class InformationDiscrepancy extends RuntimeException{
    public InformationDiscrepancy() {
    }

    public InformationDiscrepancy(String message) {
        super(message);
    }

    public InformationDiscrepancy(String message, Throwable cause) {
        super(message, cause);
    }

    public InformationDiscrepancy(Throwable cause) {
        super(cause);
    }

    public InformationDiscrepancy(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
