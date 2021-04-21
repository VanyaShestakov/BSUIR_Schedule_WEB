package Schedule;

import java.util.HashSet;

public class BSUIRLesson {
    private final String subjectName;
    private final String time;
    private final String teacher;
    private final String type;
    private final HashSet<Integer> weeks;
    private final String weekDay;
    private final String auditory;
    private final String teacherPhoto;

    public String getTeacherPhoto() {
        return teacherPhoto;
    }

    public BSUIRLesson(String subjectName, String time, String teacher, String type, String weekDay, HashSet<Integer> weeks, String auditory, String teacherPhoto) {
        this.subjectName = subjectName;
        this.time = time;
        this.teacher = teacher;
        this.type = type;
        this.weekDay = weekDay;
        this.weeks = weeks;
        this.auditory = auditory;
        this.teacherPhoto = teacherPhoto;

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

    public String getSubjectName() {
        return subjectName;
    }

    public String getTime() {
        return time;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getType() {
        return type;
    }

    public String getAuditory() {
        return auditory;
    }
}
