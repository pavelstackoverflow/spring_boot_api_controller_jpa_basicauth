package com.petprojects.webapi.service;

import com.petprojects.webapi.entity.Course;
import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @Test
    public void crudTest() {
        Assertions.assertThat(taskService.getStudentTask(1L)).isNotNull()
                .hasSize(3);
        Assertions.assertThat(taskService.getStudentTask(2L)).isNotNull()
                .hasSize(0);
        Student student = new Student(2L);
        Course course = new Course(2L);
        Task task = new Task("New Task", student, course);
        taskService.addTask(task);
        Assertions.assertThat(taskService.getStudentTask(1L)).isNotNull()
                .hasSize(3);
        Assertions.assertThat(taskService.getStudentTask(2L)).isNotNull()
                .hasSize(1);
        taskService.deleteTask(task);
        Assertions.assertThat(taskService.getStudentTask(1L)).isNotNull()
                .hasSize(3);
        Assertions.assertThat(taskService.getStudentTask(2L)).isNotNull()
                .hasSize(0);
    }

    @Test
    public void addNotExistId() {
        Student student = new Student(7L);
        Course course = new Course(2L);
        Task task = new Task("New Task", student, course);
        Student student2 = new Student(2L);
        Course course2 = new Course(7L);
        Task task2 = new Task("New Task", student2, course2);
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->taskService.addTask(task));
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->taskService.addTask(task2));


    }
}
