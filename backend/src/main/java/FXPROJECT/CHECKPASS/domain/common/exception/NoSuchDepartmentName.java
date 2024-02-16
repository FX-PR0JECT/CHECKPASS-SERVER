package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoSuchDepartmentName extends RuntimeException{

    public NoSuchDepartmentName() {
    }

    public NoSuchDepartmentName(String message) {
        super(message);
    }

    public NoSuchDepartmentName(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDepartmentName(Throwable cause) {
        super(cause);
    }

    public NoSuchDepartmentName(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
