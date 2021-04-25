package Schedule;

import java.util.HashSet;

public class BSUIRLesson {
    private final String subjectName;
    private final String time;
    private final String type;
    private final HashSet<Integer> weeks;
    private final String weekDay;
    private final String auditory;
    private final int subGroup;
    private final BSUIRTeacher teacher;

    public BSUIRLesson(String subjectName,
                       String time,
                       String type,
                       String weekDay,
                       HashSet<Integer> weeks,
                       String auditory,
                       int subGroup,
                       BSUIRTeacher teacher) {
        this.subjectName = subjectName;
        this.time = time;
        this.type = type;
        this.weekDay = weekDay;
        this.weeks = weeks;
        this.auditory = auditory;
        this.subGroup = subGroup;
        this.teacher = teacher;
    }

    public HashSet<Integer> getWeeks() {
        return weeks;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getTime() {
        return time;
    }

    public BSUIRTeacher getTeacher() {
        return teacher;
    }

    public String getType() {
        return type;
    }

    public String getAuditory() {
        return auditory;
    }

    public int getSubGroup() {
        return subGroup;
    }

    @Override
    public String toString() {
        return subjectName + "(" + type + ") " + time + "\n"+ "(" + auditory + ")\n" + teacher;
    }
}
