package FXPROJECT.CHECKPASS.domain.common.exception;

public class ServerMaintenance extends RuntimeException{

    public ServerMaintenance() {
    }

    public ServerMaintenance(String message) {
        super(message);
    }

    public ServerMaintenance(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerMaintenance(Throwable cause) {
        super(cause);
    }

    public ServerMaintenance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
