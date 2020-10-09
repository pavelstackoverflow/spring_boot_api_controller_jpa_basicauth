package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Student;

public class StudentSimpleDTO {

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

    public StudentSimpleDTO() {
    }

    public StudentSimpleDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentSimpleDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void convert(Student student) {
        this.id  = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
    }

    @Override
    public String toString() {
        String result = "Student{";
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
