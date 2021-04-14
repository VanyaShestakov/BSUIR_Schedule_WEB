package Schedule.ScheduleTools.Exceptions;

public class WeekDayDoesNotExistsException extends RuntimeException {
    public WeekDayDoesNotExistsException() {
    }

    public WeekDayDoesNotExistsException(String message) {
        super(message);
    }

    public WeekDayDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeekDayDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public WeekDayDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
