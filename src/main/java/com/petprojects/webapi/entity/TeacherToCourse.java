package com.petprojects.webapi.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TeacherToCourse implements Serializable {

    @EmbeddedId
    private TeacherToCourseCompositeKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    public TeacherToCourseCompositeKey getId() {
        return id;
    }

    public void setId(TeacherToCourseCompositeKey id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TeacherToCourse(TeacherToCourseCompositeKey id, Teacher teacher, Course course) {
        this.id = id;
        this.teacher = teacher;
        this.course = course;
    }

    public TeacherToCourse() {
    }


}
