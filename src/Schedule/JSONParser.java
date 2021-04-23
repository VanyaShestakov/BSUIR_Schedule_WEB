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

    private static final String LESSON_TYPE = "lessonType";
    private static final String AUDITORY = "auditory";
    private static final String WEEK_NUMBER = "weekNumber";
    private final JSONObject jsonObject;

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String RANK = "rank";
    private static final String PHOTO_LINK = "photoLink";
    private static final String ID = "id";
    private static final String FIO = "fio";

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

                String firstName = getTeacherFirstName(currDay, currPair);
                String lastName = getTeacherLastName(currDay, currPair);
                String middleName = getTeacherMiddleName(currDay, currPair);
                String rank = getTeacherRank(currDay, currPair);
                String photoLink = getTeacherPhotoLink(currDay, currPair);
                int id = getTeacherID(currDay, currPair);
                String fio = getTeacherFIO(currDay, currPair);
                BSUIRTeacher teacher = new BSUIRTeacher(firstName, lastName, middleName, rank, photoLink, id, fio);
                BSUIRLesson currLesson = new BSUIRLesson(subjectName, time, type, weekDay, weeks, auditory, subGroup, teacher);
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
            return getTeacherObj(currDay, currPair).getString(FIRST_NAME);
        } catch (JSONException e) {
            return  "-";
        }
    }

    private String getTeacherLastName(int currDay, int currPair) {
        try {
            return getTeacherObj(currDay, currPair).getString(LAST_NAME);
        } catch (JSONException e) {
            return  "-";
        }
    }

    private String getTeacherMiddleName(int currDay, int currPair) {
        try {
            return getTeacherObj(currDay, currPair).getString(MIDDLE_NAME);
        } catch (JSONException e) {
            return  "-";
        }
    }

    private String getTeacherRank(int currDay, int currPair) {
        try {
            return getTeacherObj(currDay, currPair).getString(RANK);
        } catch (JSONException e) {
            return  "-";
        }
    }

    private String getTeacherPhotoLink(int currDay, int currPair) {
        try {
            return getTeacherObj(currDay, currPair).getString("photoLink");
        } catch (JSONException e) {
            return  "CSS/Images/default.jpg";
        }
    }

    private int getTeacherID(int currDay, int currPair) {
        try {
            return getTeacherObj(currDay, currPair).getInt(ID);
        } catch (JSONException e) {
            return  -1;
        }
    }

    private String getTeacherFIO(int currDay, int currPair) {
        try {
            return getTeacherObj(currDay, currPair).getString(FIO);
        } catch (JSONException e) {
            return  "-";
        }
    }

    private JSONObject getTeacherObj(int currDay, int currPair) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getJSONArray(EMPLOYEE).
                getJSONObject(0);
    }







}
