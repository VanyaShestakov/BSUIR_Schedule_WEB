package JSON.Parsers;

import Schedule.BSUIRLesson;
import org.json.JSONObject;

import java.util.ArrayList;

public interface ScheduleParser {
    ArrayList<ArrayList<BSUIRLesson>> parseSchedule();

    String parseTodayDate();

}
