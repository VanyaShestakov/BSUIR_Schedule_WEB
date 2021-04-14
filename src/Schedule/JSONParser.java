package Schedule;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

class JSONParser {
    private static final String SCHEDULES = "schedules";
    private static final String WEEKDAY = "weekDay";
    private static final String SCHEDULE = "schedule";
    private static final String SUBJECT = "subject";
    private static final String LESSON_TIME = "lessonTime";
    private static final String EMPLOYEE = "employee";
    private static final String FIO = "fio";
    private static final String LESSON_TYPE = "lessonType";
    private static final String AUDITORY = "auditory";
    private static final String WEEK_NUMBER = "weekNumber";

    public JSONParser() {

    }

    public ArrayList<ArrayList<BSUIRLesson>> parseToList(JSONObject jsonObject) {
        ArrayList<ArrayList<BSUIRLesson>> weekDays = new ArrayList<>();
        for (int currDay = 0; currDay < jsonObject.getJSONArray(SCHEDULES).length(); currDay++) {
            int pairsAmount = jsonObject.getJSONArray(SCHEDULES).
                    getJSONObject(currDay).
                    getJSONArray(SCHEDULE).length();

            ArrayList<BSUIRLesson> currPairs = new ArrayList<>();
            for (int currPair = 0; currPair < pairsAmount; currPair++) {
                String subjectName = getSubjectName(jsonObject, currDay, currPair);
                String time = getTime(jsonObject, currDay, currPair);
                String teacher = getTeacher(jsonObject, currDay, currPair);
                String type = getType(jsonObject, currDay, currPair);
                String weekDay = getWeekday(jsonObject, currDay, currPair);
                String auditory = getAuditory(jsonObject, currDay, currPair);
                HashSet<Integer> weeks = getWeeks(jsonObject, currDay, currPair);
                System.out.println(subjectName);
                BSUIRLesson currLesson = new BSUIRLesson(subjectName, time, teacher, type, weekDay, weeks, auditory);
                currPairs.add(currLesson);
            }
            weekDays.add(currPairs);
        }
        return weekDays;
    }

    private String getSubjectName(JSONObject jsonObject, int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(SUBJECT);
    }

    private String getTime(JSONObject jsonObject, int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(LESSON_TIME);
    }

    private String getTeacher(JSONObject jsonObject, int currDay, int currPair) {
        try {
            return  jsonObject.
                    getJSONArray(SCHEDULES).
                    getJSONObject(currDay).
                    getJSONArray(SCHEDULE).
                    getJSONObject(currPair).
                    getJSONArray(EMPLOYEE).
                    getJSONObject(0).
                    getString(FIO);
        } catch (JSONException e) {
            return  "-";
        }
    }

    private String getType(JSONObject jsonObject, int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(LESSON_TYPE);
    }

    private String getWeekday(JSONObject jsonObject, int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getString(WEEKDAY);
    }

    private String getAuditory(JSONObject jsonObject, int currDay, int currPair) {
        try {
            return  jsonObject.
                    getJSONArray(SCHEDULES).
                    getJSONObject(currDay).
                    getJSONArray(SCHEDULE).
                    getJSONObject(currPair).
                    getJSONArray(AUDITORY).
                    getString(0);
        } catch (JSONException e) {
            return "-";
        }
    }

    private HashSet<Integer> getWeeks(JSONObject jsonObject, int currDay, int currPair) {
        JSONArray weeksJSON = jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getJSONArray(WEEK_NUMBER);
        HashSet<Integer> weeks = new HashSet<>();
        for (int i = 0; i < weeksJSON.length(); i++) {
            weeks.add((int) weeksJSON.get(i));
        }
        return weeks;
    }






}
