package com.petprojects.webapi.service;

import com.petprojects.webapi.entity.Question;
import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.Teacher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Test
    public void crudTest() {
        Assertions.assertThat(questionService.getStudentQuestions(2L)).isNotNull()
                .hasSize(3);
        Assertions.assertThat(questionService.getStudentQuestions(1L)).isNotNull()
                .hasSize(0);
        Assertions.assertThat(questionService.getTeacherQuestions(1L)).isNotNull()
                .hasSize(2);
        Student student = new Student(1L);
        Teacher teacher = new Teacher(1L);
        Question question = new Question("Test question", student, teacher);
        questionService.addQuestion(question);
        Assertions.assertThat(questionService.getStudentQuestions(1L)).isNotNull()
                .hasSize(1);
        Assertions.assertThat(questionService.getTeacherQuestions(1L)).isNotNull()
                .hasSize(3);
        questionService.deleteQuestion(question);
        Assertions.assertThat(questionService.getStudentQuestions(1L)).isNotNull()
                .hasSize(0);
        Assertions.assertThat(questionService.getTeacherQuestions(1L)).isNotNull()
                .hasSize(2);
    }

    @Test
    public void addNotExistId() {
        Student student = new Student(7L);
        Teacher teacher = new Teacher(1L);
        Question question = new Question("Test question", student, teacher);
        Student student2 = new Student(1L);
        Teacher teacher2 = new Teacher(7L);
        Question question2 = new Question("Test question", student2, teacher2);
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->questionService.addQuestion(question));
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->questionService.addQuestion(question2));


    }


}
