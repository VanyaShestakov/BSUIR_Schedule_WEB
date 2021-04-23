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
    private final JSONObject jsonObject;

    public JSONParser(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public ArrayList<ArrayList<BSUIRLesson>> parseToList() {
        ArrayList<ArrayList<BSUIRLesson>> weekDays = new ArrayList<>();
        for (int currDay = 0; currDay < jsonObject.getJSONArray(SCHEDULES).length(); currDay++) {
            int pairsAmount = jsonObject.getJSONArray(SCHEDULES).
                    getJSONObject(currDay).
                    getJSONArray(SCHEDULE).length();

            ArrayList<BSUIRLesson> currPairs = new ArrayList<>();
            for (int currPair = 0; currPair < pairsAmount; currPair++) {
                String subjectName = getSubjectName(currDay, currPair);
                String time = getTime(currDay, currPair);
                String type = getType(currDay, currPair);
                String weekDay = getWeekday(currDay);
                String auditory = getAuditory(currDay, currPair);
                HashSet<Integer> weeks = getWeeks(currDay, currPair);
                int subGroup = getSubGroup(currDay, currPair);

                String firstName;
                String lastName;
                String middleName;
                String rank;
                String photoLink = getTeacherPhotoLink(currDay, currPair);
                int id;
                String fio = getTeacherFIO(currDay, currPair);

                BSUIRLesson currLesson = new BSUIRLesson(subjectName, time, fio, type, weekDay, weeks, auditory, photoLink, subGroup);
                currPairs.add(currLesson);
            }
            weekDays.add(currPairs);
        }
        return weekDays;
    }

    public String getTodayDate() {
        return jsonObject.getString("todayDate");
    }

    private String getSubjectName(int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(SUBJECT);
    }

    private String getTime(int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(LESSON_TIME);
    }

    private String getType(int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(LESSON_TYPE);
    }

    private String getWeekday(int currDay) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getString(WEEKDAY);
    }

    private String getAuditory(int currDay, int currPair) {
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

    private HashSet<Integer> getWeeks(int currDay, int currPair) {
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

    private int getSubGroup(int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getInt("numSubgroup");
    }
//TEACHER
private String getTeacherFirstName(int currDay, int currPair) {
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

    private String getTeacherFIO(int currDay, int currPair) {
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

    private String getTeacherPhotoLink(int currDay, int currPair) {
        try {
            return  jsonObject.
                    getJSONArray(SCHEDULES).
                    getJSONObject(currDay).
                    getJSONArray(SCHEDULE).
                    getJSONObject(currPair).
                    getJSONArray(EMPLOYEE).
                    getJSONObject(0).
                    getString("photoLink");
        } catch (JSONException e) {
            return  "CSS/Images/default.jpg";
        }
    }






}
