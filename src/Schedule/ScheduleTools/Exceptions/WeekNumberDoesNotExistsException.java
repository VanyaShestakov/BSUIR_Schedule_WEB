package Schedule.ScheduleTools.Exceptions;

public class WeekNumberDoesNotExistsException extends RuntimeException {
    public WeekNumberDoesNotExistsException() {
    }

    public WeekNumberDoesNotExistsException(String message) {
        super(message);
    }

    public WeekNumberDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeekNumberDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public WeekNumberDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
