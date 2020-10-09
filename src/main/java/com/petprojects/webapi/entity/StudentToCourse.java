package com.petprojects.webapi.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StudentToCourse implements Serializable {

    @EmbeddedId
    private StudentToCourseCompositeKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private Integer mark;

    public StudentToCourseCompositeKey getId() {
        return id;
    }

    public void setId(StudentToCourseCompositeKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public StudentToCourse(StudentToCourseCompositeKey id, Student student, Course course, Integer mark) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.mark = mark;
    }

    public StudentToCourse(StudentToCourseCompositeKey id) {
        this.id = id;
    }



    public StudentToCourse(StudentToCourseCompositeKey id, Integer mark) {
        this.id = id;
        this.mark = mark;
    }

    public StudentToCourse() {
    }

    public StudentToCourse(Student student, Course course, Integer mark) {
        this.student = student;
        this.course = course;
        this.mark = mark;
    }

    public StudentToCourse(StudentToCourseCompositeKey id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }
}
