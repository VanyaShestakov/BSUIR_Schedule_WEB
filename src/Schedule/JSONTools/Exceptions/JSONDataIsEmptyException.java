package Schedule.JSONTools.Exceptions;

public class JSONDataIsEmptyException extends RuntimeException {
    public JSONDataIsEmptyException() {
    }

    public JSONDataIsEmptyException(String message) {
        super(message);
    }

    public JSONDataIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSONDataIsEmptyException(Throwable cause) {
        super(cause);
    }

    public JSONDataIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
