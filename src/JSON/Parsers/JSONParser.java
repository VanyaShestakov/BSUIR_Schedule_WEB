package JSON.Parsers;


import Schedule.BSUIRGroup;
import Schedule.BSUIRLesson;
import Schedule.BSUIRTeacher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class JSONParser implements ScheduleParser, TeachersParser, GroupsParser {
    private static final String SCHEDULES = "schedules";
    private static final String WEEKDAY = "weekDay";
    private static final String SCHEDULE = "schedule";
    private static final String SUBJECT = "subject";
    private static final String LESSON_TIME = "lessonTime";
    private static final String EMPLOYEE = "employee";

    private static final String LESSON_TYPE = "lessonType";
    private static final String AUDITORY = "auditory";
    private static final String WEEK_NUMBER = "weekNumber";

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String RANK = "rank";
    private static final String PHOTO_LINK = "photoLink";
    private static final String ID = "id";
    private static final String FIO = "fio";

    private static final String GROUP_NAME = "name";
    private static final String COURSE = "course";


    private final String jsonString;
   // private JSONArray jsonArray;

    public JSONParser(String jsonString) {
        this.jsonString = jsonString;
    }

    /**
     *The method parses a json string into a {@code ArrayList<ArrayList<BSUIRLesson>>}
     * that contains the students' schedule by week and day.
     * @return {@code ArrayList<ArrayList<BSUIRLesson>>}
     */
    public ArrayList<ArrayList<BSUIRLesson>> parseSchedule() {
        JSONObject jsonObject = new JSONObject(jsonString);
        ArrayList<ArrayList<BSUIRLesson>> weekDays = new ArrayList<>(7);
        for (int currDay = 0; currDay < 7 /*jsonObject.getJSONArray(SCHEDULES).length()*/; currDay++) {
            int pairsAmount;
            try {
                pairsAmount = jsonObject.getJSONArray(SCHEDULES).
                        getJSONObject(currDay).
                        getJSONArray(SCHEDULE).length();
            } catch (JSONException e) {
                pairsAmount = 0;
            }

            ArrayList<BSUIRLesson> currPairs = new ArrayList<>();
            for (int currPair = 0; currPair < pairsAmount; currPair++) {
                String subjectName = getSubjectName(currDay, currPair, jsonObject);
                String time = getTime(currDay, currPair, jsonObject);
                String type = getType(currDay, currPair, jsonObject);
                String weekDay = getWeekday(currDay, jsonObject);
                String auditory = getAuditory(currDay, currPair, jsonObject);
                HashSet<Integer> weeks = getWeeks(currDay, currPair, jsonObject);
                int subGroup = getSubGroup(currDay, currPair, jsonObject);

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

    public String parseTodayDate() {
        return new JSONObject(jsonString).getString("todayDate");
    }

    public ArrayList<BSUIRTeacher> parseTeachers() {
        JSONArray jsonArray = new JSONArray(jsonString);
        ArrayList<BSUIRTeacher> teachers = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String firstName = getTeacherFirstName(jsonArray.getJSONObject(i));
            String lastName = getTeacherLastName(jsonArray.getJSONObject(i));
            String middleName = getTeacherMiddleName(jsonArray.getJSONObject(i));
            String rank = getTeacherRank(jsonArray.getJSONObject(i));
            String photoLink = getTeacherPhotoLink(jsonArray.getJSONObject(i));
            int id = getTeacherID(jsonArray.getJSONObject(i));
            String fio = getTeacherFIO(jsonArray.getJSONObject(i));
            teachers.add(new BSUIRTeacher(firstName, lastName, middleName, rank, photoLink, id, fio));
        }
        return teachers;
    }

    public ArrayList<BSUIRGroup> parseGroups() {
        JSONArray jsonArray = new JSONArray(jsonString);
        ArrayList<BSUIRGroup> groups = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            int id = getGroupID(jsonArray.getJSONObject(i));
            String name = getGroupName(jsonArray.getJSONObject(i));
            int course = getGroupCourse(jsonArray.getJSONObject(i));
            groups.add(new BSUIRGroup(id, name, course));
        }
        return groups;
    }

    private int getGroupID(JSONObject obj) {
        return obj.getInt(ID);
    }

    private String getGroupName(JSONObject obj) {
        return obj.getString(GROUP_NAME);
    }

    private int getGroupCourse(JSONObject obj) {
        return obj.optInt(COURSE, -1);
    }

    private String getTeacherFirstName(JSONObject obj) {
        return obj.getString(FIRST_NAME);
    }

    private String getTeacherLastName(JSONObject obj) {
        return obj.getString(LAST_NAME);
    }

    private String getTeacherMiddleName(JSONObject obj) {
        return obj.getString(MIDDLE_NAME);
    }

    private String getTeacherRank(JSONObject obj) {
        try {
            return obj.getString(RANK);
        } catch (JSONException e) {
            return "-";
        }
    }

    private String getTeacherPhotoLink(JSONObject obj) {
        return obj.getString(PHOTO_LINK);
    }

    private int getTeacherID(JSONObject obj) {
        return obj.getInt(ID);
    }

    private String getTeacherFIO(JSONObject obj) {
        return obj.getString(FIO);
    }

    private String getSubjectName(int currDay, int currPair, JSONObject jsonObject) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(SUBJECT);
    }

    private String getTime(int currDay, int currPair, JSONObject jsonObject) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(LESSON_TIME);
    }

    private String getType(int currDay, int currPair, JSONObject jsonObject) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getString(LESSON_TYPE);
    }

    private String getWeekday(int currDay, JSONObject jsonObject) {
        return  jsonObject.
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getString(WEEKDAY);
    }

    private String getAuditory(int currDay, int currPair, JSONObject jsonObject) {
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

    private HashSet<Integer> getWeeks(int currDay, int currPair, JSONObject jsonObject) {
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

    private int getSubGroup(int currDay, int currPair, JSONObject jsonObject) {
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
        return  new JSONObject(jsonString).
                getJSONArray(SCHEDULES).
                getJSONObject(currDay).
                getJSONArray(SCHEDULE).
                getJSONObject(currPair).
                getJSONArray(EMPLOYEE).
                getJSONObject(0);
    }



}
