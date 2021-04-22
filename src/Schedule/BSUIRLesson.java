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
    private final int subGroup;

    public BSUIRLesson(String subjectName, String time, String teacher, String type, String weekDay,
                       HashSet<Integer> weeks, String auditory, String teacherPhoto, int subGroup) {
        this.subjectName = subjectName;
        this.time = time;
        this.teacher = teacher;
        this.type = type;
        this.weekDay = weekDay;
        this.weeks = weeks;
        this.auditory = auditory;
        this.teacherPhoto = teacherPhoto;
        this.subGroup = subGroup;
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

    public String getTeacher() {
        return teacher;
    }

    public String getType() {
        return type;
    }

    public String getAuditory() {
        return auditory;
    }

    public String getTeacherPhoto() {
        return teacherPhoto;
    }

    public int getSubGroup() {
        return subGroup;
    }

    @Override
    public String toString() {
        return subjectName + "(" + type + ") " + time + "\n"+ "(" + auditory + ")\n" + teacher;
    }
}
