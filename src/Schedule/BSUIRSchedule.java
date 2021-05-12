package Schedule;

import JSON.Parsers.JSONParser;
import JSON.JSONRequester;
import JSON.Parsers.ScheduleParser;
import org.json.JSONObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class BSUIRSchedule {
    private final ArrayList<ArrayList<BSUIRLesson>> scheduleList;
    private final int currentWeek;

    @NotEmpty
    @Pattern(regexp = "^\\d+$")
    @Size(min = 6, max = 6)
    private final String groupNumber;
    private final String todayDate;

    public String getTodayDate() {
        return todayDate;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public BSUIRSchedule(String groupNumber) {
        JSONRequester requester = new JSONRequester();
        String jsonData = requester.getGroupSchedule(groupNumber);
        ScheduleParser parser = new JSONParser(jsonData);
        this.currentWeek = requester.getCurrentWeek();
        this.scheduleList = parser.parseSchedule();
        this.groupNumber = groupNumber;
        this.todayDate = parser.parseTodayDate();
    }

    public ArrayList<ArrayList<BSUIRLesson>> getScheduleList() {
        return scheduleList;
    }

}
