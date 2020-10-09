package com.petprojects.webapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<TeacherToCourse> teachers;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<StudentToCourse> students;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<Task> tasks;

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

    public Set<TeacherToCourse> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherToCourse> teachers) {
        this.teachers = teachers;
    }

    public Set<StudentToCourse> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentToCourse> students) {
        this.students = students;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Course(Long id, String courseName, Set<TeacherToCourse> teachers, Set<StudentToCourse> students, Set<Task> tasks) {
        this.id = id;
        this.courseName = courseName;
        this.teachers = teachers;
        this.students = students;
        this.tasks = tasks;
    }

    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

}
