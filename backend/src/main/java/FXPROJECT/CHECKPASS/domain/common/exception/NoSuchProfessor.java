package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoSuchProfessor extends RuntimeException{

    public NoSuchProfessor() {
    }

    public NoSuchProfessor(String message) {
        super(message);
    }

    public NoSuchProfessor(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchProfessor(Throwable cause) {
        super(cause);
    }

    public NoSuchProfessor(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
