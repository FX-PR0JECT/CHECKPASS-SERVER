package FXPROJECT.CHECKPASS.domain.common.exception;

public class SanctionsUser extends RuntimeException{
    public SanctionsUser() {
    }

    public SanctionsUser(String message) {
        super(message);
    }

    public SanctionsUser(String message, Throwable cause) {
        super(message, cause);
    }

    public SanctionsUser(Throwable cause) {
        super(cause);
    }

    public SanctionsUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
