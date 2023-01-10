package model;

public class Student {

    private Integer student_id;
    private String name;
    private String lastname;
    private String patronymic;
    private String birthdate;
    private String group_name;

    public Student(Integer student_id, String name, String lastname, String patronymic, String birthdate, String group_name) {
        this.student_id = student_id;
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.group_name = group_name;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
