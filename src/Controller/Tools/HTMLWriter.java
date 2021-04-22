package Controller.Tools;

import Schedule.BSUIRLesson;
import Schedule.BSUIRSchedule;

import java.text.SimpleDateFormat;
import java.util.*;

public class HTMLWriter {


    private static final String GREEN_BORDER_STYLE = "3px solid #00ff80";
    private static final String RED_BORDER_STYLE = "3px solid red";
    private static final String YELLOW_BORDER_STYLE = "3px solid yellow";
    private final BSUIRSchedule schedule;
    private final ArrayList<String> weekDays = new ArrayList<>(Arrays.asList
       ("понедельник",
        "вторник",
        "среда",
        "четверг",
        "пятница",
        "суббота",
        "воскресенье"));

    public HTMLWriter(BSUIRSchedule schedule) {
        this.schedule = schedule;
    }

    public String getHTMLSchedule () {
        ArrayList<ArrayList<BSUIRLesson>> list = schedule.getScheduleList();
        int currentWeek = schedule.getCurrentWeek();
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> usedWeeks = new HashSet<>();
        Date currDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        int weekDayIndex = getWeekDayIndex();
        do {
            usedWeeks.add(currentWeek);
            sb.append("<br><center><h2>"+ "Неделя: " + currentWeek + "</h2></center>");
            for (int i = weekDayIndex; i < list.size(); i++) {
                String mark = sdf.format(currDate).equals(list.get(i).get(0).getWeekDay().toLowerCase()) &&
                        currentWeek == schedule.getCurrentWeek() ? " (Сегодня)" : "";
                sb.append("<br><h2>" + list.get(i).get(0).getWeekDay() + mark + "</h2>");

                for (int j = 0; j < list.get(i).size(); j++) {
                    BSUIRLesson currLesson = list.get(i).get(j);
                    if (currLesson.getWeeks().contains(currentWeek)){
                        String borderStyle = getBorderStyle(currLesson.getType());
                        String subGroup = String.valueOf(currLesson.getSubGroup());
                        subGroup = subGroup.equals("0") ? "Вся группа" : subGroup;
                        sb.append("<div style=\"border:" + borderStyle + "\" class=\"lesson_container\">\n" +
                                "<img class=\"teacher_photo\" src=\"" + currLesson.getTeacherPhoto() + "\">"+
                                "<div class=\"lesson_info\">" +
                                "<h2>" + currLesson.getSubjectName() + "  (" +
                                currLesson.getType()  + ")    "  + currLesson.getTime() + "</h2>" +
                                " <p class=\"text\">" +
                                "Аудитория: " + currLesson.getAuditory() + "<br>" +
                                "Преподаватель: " + currLesson.getTeacher() + "<br>" +
                                "Подгруппа: " + subGroup +
                                "</p>" + "</div>" + "</div>");
                    }
                }
            }
            weekDayIndex = 0;
            currentWeek++;
            currentWeek = currentWeek > 4 ? 1 : currentWeek ;
        }while (!usedWeeks.contains(currentWeek));
        return sb.toString();
    }

    public String getScheduleInfo() {
        return "<div class=\"lesson_container\">\n"+
                " <p class=\"text\">Номер группы: " + schedule.getGroupNumber() + "</p>" +
                " <p class=\"text\">Текущая неделя: " + schedule.getCurrentWeek() + "</p>" +
                " <p class=\"text\">Текущая дата: " + schedule.getTodayDate() + "</p>" +"</div>";
    }

    private int getWeekDayIndex() {
        Date currDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return weekDays.indexOf(sdf.format(currDate));
    }

    private String getBorderStyle(String lessonType) {
        String borderStyle = "";
        switch (lessonType) {
            case "ЛК" -> borderStyle = GREEN_BORDER_STYLE;
            case "ПЗ" -> borderStyle = YELLOW_BORDER_STYLE;
            case "ЛР" -> borderStyle = RED_BORDER_STYLE;
        }
        return borderStyle;
    }

}
