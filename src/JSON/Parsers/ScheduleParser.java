package JSON.Parsers;

import Schedule.BSUIRLesson;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Interface providing the functionality of getting a schedule for a group
 * by parsing json strings into JAVA data structures.
 * JSONParser class implements this interface.
 */

public interface ScheduleParser {

    ArrayList<ArrayList<BSUIRLesson>> parseSchedule();

    String parseTodayDate();

}
