package Schedule;

import JSON.JSONRequester;
import JSON.JSONScheduleParser;
import org.json.JSONObject;

import java.util.ArrayList;

public class BSUIRSchedule {
    private static final String TITLE_SEPARATOR = "------------------------\n";
    private static final int MIN_WEEK_NUMBER = 1;
    private static final int MAX_WEEK_NUMBER = 1;
    private final ArrayList<ArrayList<BSUIRLesson>> scheduleList;
    private final int currentWeek;
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
        JSONScheduleParser parser = new JSONScheduleParser( new JSONObject(jsonData));
        this.currentWeek = requester.getCurrentWeek();
        this.scheduleList = parser.parseToList();
        this.groupNumber = groupNumber;
        this.todayDate = parser.getTodayDate();
    }

    public ArrayList<ArrayList<BSUIRLesson>> getScheduleList() {
        return scheduleList;
    }

}
