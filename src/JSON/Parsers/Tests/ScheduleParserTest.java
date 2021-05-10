package JSON.Parsers.Tests;

import JSON.Parsers.JSONParser;
import JSON.Parsers.ScheduleParser;
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

/**
 * Testing by {@code JUnit5}.
 * The class tests the correctness of parsing the JSON format.
 * The tested interface: {@code ScheduleParser}.
 *
 * How testing works:
 * JSON format is read from a local file (for reliable results at any time).
 * After that, the main content of the read file is checked.
 */
class ScheduleParserTest {

    private static final String FILE_WITH_JSON_PATH = "src\\JSON\\Parsers\\Tests\\scheduleJSON.txt";
    private static final int WEEKDAYS_AMOUNT = 7;
    private static final int SATURDAY_INDEX = 6;
    private static final int MONDAY_PAIRS_AMOUNT = 7;
    private static final String EXPECTED_DAY = "09.05.2021";

    private static ArrayList<ArrayList<BSUIRLesson>> scheduleList;
    private static String todayDate;

    @BeforeAll
    static void getSchedule() {
        String jsonData = readJSONFromFile();
        ScheduleParser parser = new JSONParser(jsonData);
        scheduleList = parser.parseSchedule();
        todayDate = parser.parseTodayDate();
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
    @DisplayName("Amount of weekdays should be 7")
    void amountOfWeekDaysShouldBeSeven() {
        Assertions.assertEquals(WEEKDAYS_AMOUNT, scheduleList.size());
    }

    @Test
    @DisplayName("Number of pairs on Saturday must be 0")
    void numberOfPairsOnSaturdayMustBeZero () {
        Assertions.assertEquals(0, scheduleList.get(SATURDAY_INDEX).size());
    }

    @Test
    @DisplayName("Number of pairs on Monday must be 7")
    void numberOfPairsOnMondayInFullScheduleMustBeSeven() {
        Assertions.assertEquals(MONDAY_PAIRS_AMOUNT, scheduleList.get(0).size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    @DisplayName("Any day of the week must contain BSUIRLesson objects (expect Saturday)")
    void anyWeekDayMustContainsBSUIRLessonObj(int weekDay) {
        if (weekDay != 6)
            Assertions.assertSame(BSUIRLesson.class, scheduleList.get(weekDay).get(0).getClass());
        else
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scheduleList.get(weekDay).get(0));
    }

    @Test
    @DisplayName("Any lesson must contain BSUIRTeacher object")
    void anyLessonMustContainBSUIRTeacherObj() {
        Assertions.assertSame(BSUIRTeacher.class, scheduleList.get(0).get(0).getTeacher().getClass());
    }

    @Test
    @DisplayName("Monday must contain 3 English pairs")
    void mondayMustContainThreeEnglishPairs() {
        Assertions.assertEquals(3, scheduleList.get(0).stream().filter(lesson -> lesson.getSubjectName().equals("ИнЯз")).count());
    }

    @Test
    void parseTodayDate() {
        Assertions.assertEquals(EXPECTED_DAY, todayDate);
    }
}