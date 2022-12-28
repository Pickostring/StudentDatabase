package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "groups")
public class Group {

    @Id
    private String group_name;
    private String facultu;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getFacultu() {
        return facultu;
    }

    public void setFacultu(String facultu) {
        this.facultu = facultu;
    }


}
