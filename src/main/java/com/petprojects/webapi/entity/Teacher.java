package com.petprojects.webapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherToCourse> courses;

    @OneToMany(mappedBy = "teacher")
    private Set<Question> questions;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<TeacherToCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<TeacherToCourse> courses) {
        this.courses = courses;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Teacher(Long id, String firstName, String lastName, Set<Student> students) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.students = students;
    }

    public Teacher(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher(Long id) {
        this.id = id;
    }

    public Teacher() {
    }


}
