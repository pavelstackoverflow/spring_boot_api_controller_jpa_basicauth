package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Teacher;

import java.util.Set;

public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;

    private Set<StudentSimpleDTO> students;

    private Set<CourseSimpleDTO> courses;

    private Set<QuestionDTO> questions;

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

    public Set<StudentSimpleDTO> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentSimpleDTO> students) {
        this.students = students;
    }

    public Set<CourseSimpleDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseSimpleDTO> courses) {
        this.courses = courses;
    }

    public Set<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDTO> questions) {
        this.questions = questions;
    }

    public TeacherDTO(Long id, String firstName, String lastName, Set<StudentSimpleDTO> students) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.students = students;
    }

    public TeacherDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TeacherDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TeacherDTO() {
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
        if (students!=null) {
            if (students.size()>0) {
                result+=",students=(";
                StringBuilder stringBuilder = new StringBuilder();
                for (StudentSimpleDTO student : this.students){
                    stringBuilder.append(student.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + ")";
            } else {
                result+=",students=0 elements";
            }

        } else {
            result+=",students=null";
        }
        if (courses!=null) {
            if (courses.size()>0) {
                result+=",courses=[";
                StringBuilder stringBuilder = new StringBuilder();
                for (CourseSimpleDTO course : this.courses){
                    stringBuilder.append(course.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + "]";
            } else {
                result+=",courses=0 elements";
            }
        } else {
            result+=",courses=null";
        }
        if (questions!=null) {
            if (questions.size()>0) {
                result+=",questions=[";
                StringBuilder stringBuilder = new StringBuilder();
                for (QuestionDTO question : this.questions){
                    stringBuilder.append(question.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + "]";
            } else {
                result+=",questions=0 elements";
            }
        } else {
            result+=",questions=null";
        }
        result+=")";

        return result;
    }
}
