package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoSuchUser extends RuntimeException{

    public NoSuchUser() {
    }

    public NoSuchUser(String message) {
        super(message);
    }

    public NoSuchUser(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUser(Throwable cause) {
        super(cause);
    }

    public NoSuchUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
