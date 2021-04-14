package Schedule;


import Schedule.JSONTools.Exceptions.JSONDataIsEmptyException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class JSONRequester {
    private static final String GROUP_SCHEDULE_URL = "https://journal.bsuir.by/api/v1/studentGroup/schedule?studentGroup=";
    private static final String CURRENT_WEEK_URL = "https://journal.bsuir.by/api/v1/week";

    public String getGroupSchedule(String groupNumber) {
        try {
            URL url = new URL(GROUP_SCHEDULE_URL + groupNumber);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                String line;
                StringBuilder jsonData = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    jsonData.append(line);
                }
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new JSONDataIsEmptyException("Schedule.JSON data is empty");
    }

    public int getCurrentWeek() {
        try {
            URL url = new URL(CURRENT_WEEK_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                return Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new JSONDataIsEmptyException("Schedule.JSON data is empty");
    }
}
