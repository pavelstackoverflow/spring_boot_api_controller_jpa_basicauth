package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Question;

public class QuestionDTO {

    private Long id;
    private String question;

    private StudentSimpleDTO student;

    private TeacherSimpleDTO teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public StudentSimpleDTO getStudent() {
        return student;
    }

    public void setStudent(StudentSimpleDTO student) {
        this.student = student;
    }

    public TeacherSimpleDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherSimpleDTO teacher) {
        this.teacher = teacher;
    }

    public void convert(Question question) {
        this.id  = question.getId();
        this.question = question.getQuestion();
    }

    public QuestionDTO(Long id, String question, StudentSimpleDTO student, TeacherSimpleDTO teacher) {
        this.id = id;
        this.question = question;
        this.student = student;
        this.teacher = teacher;
    }

    public QuestionDTO() {
    }

    @Override
    public String toString() {
        String result = "Question{";
        if (id!=null) {
            result+="id=" + id;
        } else {
            result+="id=null";
        }
        if (question!=null) {
            result+=",question=" + question;
        } else {
            result+=",question=null";
        }
        if (student!=null) {
            result+="," + student.toString();
        } else {
            result+=",student=null";
        }
        if (teacher!=null) {
            result+="," + teacher.toString();
        } else {
            result+=",teacher=null";
        }
        result+=")";
        return result;
    }
}
