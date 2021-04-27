package Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONTeachersParser {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String RANK = "rank";
    private static final String PHOTO_LINK = "photoLink";
    private static final String ID = "id";
    private static final String FIO = "fio";

    private final JSONArray jsonArray;

    public JSONTeachersParser(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public ArrayList<BSUIRTeacher> parseToList() {
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
}
