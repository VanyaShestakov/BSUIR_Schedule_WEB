package Schedule;

import java.util.Optional;

public class BSUIRGroup {
    private int id;
    private String name;
    private int course;

    public BSUIRGroup(int id, String name, int course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }
}
