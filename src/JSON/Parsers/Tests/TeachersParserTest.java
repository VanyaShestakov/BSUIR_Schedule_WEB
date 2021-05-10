package JSON.Parsers.Tests;

import JSON.Parsers.JSONParser;
import JSON.Parsers.ScheduleParser;
import JSON.Parsers.TeachersParser;
import Schedule.BSUIRLesson;
import Schedule.BSUIRTeacher;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeachersParserTest {

    private static final String FILE_WITH_JSON_PATH = "src\\JSON\\Parsers\\Tests\\teachersJSON.txt";
    private static ArrayList<BSUIRTeacher> teachers;

    @BeforeAll
    static void getSchedule() {
        String jsonData = readJSONFromFile();
        TeachersParser parser = new JSONParser(jsonData);
        teachers = parser.parseTeachers();
    }

    private static String readJSONFromFile() {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader bfReader = new BufferedReader(new FileReader(FILE_WITH_JSON_PATH))) {
            while ((line = bfReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Test
    void amountOfTeachersMustBeTwentyFive() {
        Assertions.assertEquals(25, teachers.size());
    }
}