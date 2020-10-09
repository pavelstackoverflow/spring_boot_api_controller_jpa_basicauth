package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.StudentToCourse;

public class StudentToCourseDTO {

    private StudentSimpleDTO student;
    private CourseSimpleDTO course;
    private Integer mark;

    public StudentSimpleDTO getStudent() {
        return student;
    }

    public void setStudent(StudentSimpleDTO student) {
        this.student = student;
    }

    public CourseSimpleDTO getCourse() {
        return course;
    }

    public void setCourse(CourseSimpleDTO course) {
        this.course = course;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public void convert(StudentToCourse studentToCourse) {
        this.mark  = studentToCourse.getMark();
    }

    @Override
    public String toString() {
        String result = "StudentToCourse{";
        if (mark!=null) {
            result+="mark=" + mark;
        } else {
            result+="mark=null";
        }
        if (student!=null) {
            result+="," + student.toString();
        } else {
            result+=",student=null";
        }
        if (course!=null) {
            result+="," + course.toString();
        } else {
            result+=",course=null";
        }
        result+=")";
        return result;
    }
}
