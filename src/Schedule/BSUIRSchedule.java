package Schedule;

import Schedule.ScheduleTools.Exceptions.WeekDayDoesNotExistsException;
import Schedule.ScheduleTools.Exceptions.WeekNumberDoesNotExistsException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BSUIRSchedule {
    private static final String TITLE_SEPARATOR = "------------------------\n";
    private static final int MIN_WEEK_NUMBER = 1;
    private static final int MAX_WEEK_NUMBER = 1;
    private final ArrayList<ArrayList<BSUIRLesson>> scheduleList;
    private final int currentWeek;

    public BSUIRSchedule(String groupNumber) {
        JSONRequester requester = new JSONRequester();
        String jsonData = requester.getGroupSchedule(groupNumber);
        currentWeek = requester.getCurrentWeek();
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONParser parser = new JSONParser();
        scheduleList = parser.parseToList(jsonObject);
    }

    @Override
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Week:").append(currentWeek).append("\n");
        for (int i = 0; i < scheduleList.size(); i++) {
            sb.append(TITLE_SEPARATOR);
            sb.append(scheduleList.get(i).get(0).getWeekDay()).append("\n");
            sb.append(TITLE_SEPARATOR);
            for (int j = 0; j < scheduleList.get(i).size(); j++) {
                sb.append(scheduleList.get(i).get(j)).append("\n").append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getForCurrentWeekHTML() {
        return getForWeek(currentWeek).replace("\n", "<br>");
    }

    public String getForWeekDay(int weekDay) {
        StringBuilder sb = new StringBuilder();
        if (weekDay < 0 || weekDay > 6) {
            throw new WeekDayDoesNotExistsException("Week day should be in range [0; 6]");
        }
        if (weekDay > scheduleList.size() - 1) {
            sb.append("В этот день занятий нет");
            return sb.toString();
        }
        sb.append(TITLE_SEPARATOR);
        sb.append(scheduleList.get(weekDay).get(0).getWeekDay()).append("\n");
        sb.append(TITLE_SEPARATOR);
        for (int j = 0; j < scheduleList.get(weekDay).size(); j++) {
            sb.append(scheduleList.get(weekDay).get(j)).append("\n").append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    public String getForCurrentWeek() {
        return getForWeek(currentWeek);
    }

    public String getForWeek(int weekNumber) {
        if (weekNumber < 1 || weekNumber > 4) {
            throw new WeekNumberDoesNotExistsException("Week number should be in range [1; 4]");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Week:").append(currentWeek).append("\n");
        for (int i = 0; i < scheduleList.size(); i++) {
            sb.append(TITLE_SEPARATOR);
            sb.append(scheduleList.get(i).get(0).getWeekDay()).append("\n");
            sb.append(TITLE_SEPARATOR);
            for (int j = 0; j < scheduleList.get(i).size(); j++) {
                if (scheduleList.get(i).get(j).getWeeks().contains(weekNumber)){
                    sb.append(scheduleList.get(i).get(j)).append("\n").append("\n");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getLesson() {
        return "Привет\n";
    }

}
