package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Course;

public class CourseSimpleDTO {

    private Long id;
    private String courseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseSimpleDTO(Long id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public CourseSimpleDTO() {
    }

    public void convert(Course course) {
        this.id  = course.getId();
        this.courseName  = course.getCourseName();
    }

    @Override
    public String toString() {
        String result = "Course{";
        if (id!=null) {
            result+="id=" + id;
        } else {
            result+="id=null";
        }
        if (courseName!=null) {
            result+=",courseName=" + courseName;
        } else {
            result+=",courseName=null";
        }
        result+=")";
        return result;
    }
}
