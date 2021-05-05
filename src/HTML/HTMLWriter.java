package HTML;

import Schedule.BSUIRLesson;
import Schedule.BSUIRSchedule;
import Schedule.BSUIRTeacher;

import java.text.SimpleDateFormat;
import java.util.*;

public class HTMLWriter {
    private static final String GREEN_BORDER_STYLE = "3px solid #00ff80";
    private static final String RED_BORDER_STYLE = "3px solid red";
    private static final String YELLOW_BORDER_STYLE = "3px solid yellow";
    private BSUIRSchedule schedule;
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

    public HTMLWriter() {

    }

    public String getHTMLSchedule () {
        ArrayList<ArrayList<BSUIRLesson>> list = schedule.getScheduleList();
        int currentWeek = schedule.getCurrentWeek();
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> usedWeeks = new HashSet<>();
        Date currDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        int weekDayIndex = getWeekDayIndex();
        String mark = "(Сегодня)";
        String dayInfo;
        do {
            usedWeeks.add(currentWeek);
            sb.append("<br><center><h2>"+ "Неделя: " + currentWeek + "</h2></center>");
            for (int i = weekDayIndex; i < list.size(); i++) {
                dayInfo = weekDays.get(i).toUpperCase(Locale.ROOT) + mark + " " + sdf.format(calendar.getTime());
                sb.append("<br><h2 align=\"justify\">" + dayInfo + "</h2>");
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                if (list.get(i).size() != 0) {
                    for (int j = 0; j < list.get(i).size(); j++) {
                        BSUIRLesson currLesson = list.get(i).get(j);
                        if (currLesson.getWeeks().contains(currentWeek)) {
                            String borderStyle = getBorderStyle(currLesson.getType());
                            String subGroup = String.valueOf(currLesson.getSubGroup());
                            subGroup = subGroup.equals("0") ? "Вся группа" : subGroup;
                            sb.append("<div style=\"border:" + borderStyle + "\" class=\"lesson_container\">\n" +
                                    "<img class=\"teacher_photo\" src=\"" + currLesson.getTeacher().getPhotoLink() + "\">" +
                                    "<div class=\"lesson_info\">" +
                                    "<h2>" + currLesson.getSubjectName() + "  (" +
                                    currLesson.getType() + ")    " + currLesson.getTime() + "</h2>" +
                                    " <p class=\"text\">" +
                                    "Аудитория: " + currLesson.getAuditory() + "<br>" +
                                    "Преподаватель: " + currLesson.getTeacher().getFio() + "<br>" +
                                    "Подгруппа: " + subGroup +
                                    "</p>" + "</div>" + "</div>");
                        }
                    }
                } else {
                    sb.append("<div class=\"lesson_container\"><center><h2>Занятий нет</h2></center></div>");
                }
                mark = "";
            }
            weekDayIndex = 0;
            currentWeek++;
            currentWeek = currentWeek > 4 ? 1 : currentWeek ;
        }while (!usedWeeks.contains(currentWeek));
        return sb.toString();
    }

    public String getTeachersInfo(ArrayList<BSUIRTeacher> teachers) {
        teachers.sort((Comparator.comparing(BSUIRTeacher::getLastName)));
        String info = "";
        for (BSUIRTeacher teacher: teachers) {
            info += "<div height=\"200px\" class=\"lesson_container\">\n" +
                    "<img width=\"120px\" class=\"teacher_photo\" src=\"" + teacher.getPhotoLink() + "\">" +
                    "<div class=\"lesson_info\">" +
                    " <p class=\"text\"> <h2>" +
                    teacher.getLastName() + " " +
                    teacher.getFirstName() + " " +
                    teacher.getMiddleName() + "<br></h2>" +
                    teacher.getRank() +
                    "</p>" + "</div>" + "</div>";
        }
        return info;
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
