package JSON.Parsers.Tests;

import JSON.Parsers.JSONParser;
import JSON.Parsers.ScheduleParser;
import JSON.Parsers.TeachersParser;
import Schedule.BSUIRLesson;
import Schedule.BSUIRTeacher;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TeachersParserTest {

    private static final String FILE_WITH_JSON_PATH = "src\\JSON\\Parsers\\Tests\\teachersJSON.txt";
    private static final int TEACHERS_AMOUNT = 25;

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
    @DisplayName("Amount of teachers must be 25")
    void amountOfTeachersMustBeTwentyFive() {
        Assertions.assertEquals(TEACHERS_AMOUNT, teachers.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Андрианова", "Андреева", "Анисимов", "Антипенко"})
    @DisplayName("List of teachers must contains teachers with surnames: Андрианова, Андреева, Анисимов, Антипенко.")
    void teachersListMustContainsSomeTeachers(String lastName){
        Optional<BSUIRTeacher> optTeacher = teachers.parallelStream().filter(obj -> obj.getLastName().equals(lastName)).findFirst();
        Assertions.assertTrue(optTeacher.isPresent());
    }

    @Test
    @DisplayName("First teacher must have ID: 500434")
    void checkFirstTeacher() {
        Assertions.assertEquals(500434, teachers.get(0).getId());
    }

    @Test
    @DisplayName("Last teacher must have ID: 505738")
    void checkLastTeacher() {
        Assertions.assertEquals(505738, teachers.get(teachers.size() - 1).getId());
    }
}