package Utils;

public class UserData {
    private String name;
    private String id;
    private String sex;
    private String dateOfBirth;
    private String motherName;
    private String fatherName;
    private String email;
    private String imageUrl;

    public UserData(){}

    public UserData(String name, String id, String sex, String dateOfBirth, String motherName, String fatherName, String email, String imageUrl) {
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
