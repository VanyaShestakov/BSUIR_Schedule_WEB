package Schedule;

import java.util.HashSet;

class BSUIRLesson {
    private final String subjectName;
    private final String time;
    private final String teacher;
    private final String type;
    private final HashSet<Integer> weeks;
    private final String weekDay;
    private final String auditory;

    public BSUIRLesson(String subjectName, String time, String teacher, String type, String weekDay, HashSet<Integer> weeks, String auditory) {
        this.subjectName = subjectName;
        this.time = time;
        this.teacher = teacher;
        this.type = type;
        this.weekDay = weekDay;
        this.weeks = weeks;
        this.auditory = auditory;
    }

    public HashSet<Integer> getWeeks() {
        return weeks;
    }

    public String getWeekDay() {
        return weekDay;
    }

    @Override
    public String toString() {
        return subjectName + "(" + type + ") " + time + "\n"+ "(" + auditory + ")\n" + teacher;
    }

}
