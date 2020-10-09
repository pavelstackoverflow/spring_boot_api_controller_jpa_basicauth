package com.petprojects.webapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name ="student_id"),
            inverseJoinColumns = @JoinColumn(name ="teacher_id")
    )
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "student")
    private Set<StudentToCourse> courses;

    @OneToMany(mappedBy = "student")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "student")
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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<StudentToCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<StudentToCourse> courses) {
        this.courses = courses;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        for (Teacher teacher : this.teachers){
            if (teacher.getId().equals(teacherId)) {
                this.teachers.remove(teacher);
            }
        }
    }

    public Student(Long id, String firstName, String lastName, Set<Teacher> teachers, Set<StudentToCourse> courses, Set<Task> tasks, Set<Question> questions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teachers = teachers;
        this.courses = courses;
        this.tasks = tasks;
        this.questions = questions;
    }

    public Student() {
    }

    public Student(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

