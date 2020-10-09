package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Teacher;

public class TeacherSimpleDTO {

    private Long id;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TeacherSimpleDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TeacherSimpleDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TeacherSimpleDTO() {
    }

    public void convert(Teacher teacher) {
        this.id  = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
    }

    @Override
    public String toString() {
        String result = "Teacher{";
        if (id!=null) {
            result+="id=" + id;
        } else {
            result+="id=null";
        }
        if (firstName!=null) {
            result+=",firstName=" + firstName;
        } else {
            result+=",firstName=null";
        }
        if (lastName!=null) {
            result+=",lastName=" + lastName;
        } else {
            result+=",lastName=null";
        }
        result+=")";

        return result;
    }
}
