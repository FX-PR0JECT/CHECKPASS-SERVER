package FXPROJECT.CHECKPASS.domain.common.exception;

public class DupleUsers extends RuntimeException{

    public DupleUsers() {
    }

    public DupleUsers(String message) {
        super(message);
    }

    public DupleUsers(String message, Throwable cause) {
        super(message, cause);
    }

    public DupleUsers(Throwable cause) {
        super(cause);
    }

    public DupleUsers(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
