package Schedule;

public class BSUIRTeacher {
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String rank;
    private final String photoLink;
    private final int id;
    private final String fio;

    public BSUIRTeacher(String firstName, String lastName, String middleName,
                   String rank, String photoLink, int id, String fio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.rank = rank;
        this.photoLink = photoLink;
        this.id = id;
        this.fio = fio;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getRank() {
        return rank;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }
}
