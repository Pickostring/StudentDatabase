package model;

public class Group {

    private Integer group_id;

    private String group_name;

    private Integer group_number;

    private String faculty;

    public Group(String group_name, Integer group_number, String faculty) {
        this.group_name = group_name;
        this.group_number = group_number;
        this.faculty = faculty;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public void setGroup_number(Integer group_number) {
        this.group_number = group_number;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getGroup_number() {
        return group_number;
    }

    public void setGroup_number(int group_number) {
        this.group_number = group_number;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }



}
