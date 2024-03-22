package FXPROJECT.CHECKPASS.domain.common.exception;

public class NoStudentsRegisteredForTheCourse extends RuntimeException{
    public NoStudentsRegisteredForTheCourse() {
    }

    public NoStudentsRegisteredForTheCourse(String message) {
        super(message);
    }

    public NoStudentsRegisteredForTheCourse(String message, Throwable cause) {
        super(message, cause);
    }

    public NoStudentsRegisteredForTheCourse(Throwable cause) {
        super(cause);
    }

    public NoStudentsRegisteredForTheCourse(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
