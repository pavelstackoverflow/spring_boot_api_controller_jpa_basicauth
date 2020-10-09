package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.StudentDTO;
import com.petprojects.webapi.dto.StudentToCourseDTO;
import com.petprojects.webapi.entity.Course;
import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.StudentToCourse;
import com.petprojects.webapi.entity.StudentToCourseCompositeKey;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class StudentToCourseServiceTest {

    @Autowired
    StudentToCourseService studentToCourseService;

    @Autowired
    StudentService studentService;

    @Test
    public void crudTest() {

        StudentDTO studentDTO = studentService.getStudent(2L);
        Assertions.assertThat(studentDTO).isNotNull();
        Assertions.assertThat(studentDTO.getCourses()).isNotNull().hasSize(0);
        Student student = new Student(2L);
        Course course = new Course(1L);
        StudentToCourse studentToCourse1 = new StudentToCourse(new StudentToCourseCompositeKey(student.getId(), course.getId()),
                student, course);
        studentToCourseService.addStudentToCourse(studentToCourse1);
        Assertions.assertThat(studentService.getStudent(2L).getCourses()).isNotNull().hasSize(1);
        Assertions.assertThat(studentService.getStudent(2L).getCourses()).isNotNull().hasSize(1);

        StudentDTO studentDTO2 = studentService.getStudent(2L);
        for (StudentToCourseDTO studentToCourseDTO : studentDTO2.getCourses()) {
            if (studentToCourseDTO.getCourse().getId().equals(course.getId())) {
                Assertions.assertThat(studentToCourseDTO.getMark()).isNull();
            }
        }
        studentToCourse1.setMark(5);
        studentToCourseService.setMark(studentToCourse1);

        StudentDTO studentDTO3 = studentService.getStudent(2L);
        for (StudentToCourseDTO studentToCourseDTO : studentDTO3.getCourses()) {
            if (studentToCourseDTO.getCourse().getId().equals(course.getId())) {
                Assertions.assertThat(studentToCourseDTO.getMark()).isNotNull().isEqualTo(5);
            }
        }

        studentToCourseService.deleteStudentFromCourse(studentToCourse1);

        StudentDTO studentDTO4 = studentService.getStudent(2L);
        Assertions.assertThat(studentDTO4).isNotNull();
        Assertions.assertThat(studentDTO4.getCourses()).isNotNull().hasSize(0);

    }

    @Test
    public void addNotExistId() {
        Student student1 = new Student(7L);
        Course course1 = new Course(1L);
        Student student2 = new Student(2L);
        Course course2 = new Course(7L);
        StudentToCourse studentToCourse1 = new StudentToCourse(new StudentToCourseCompositeKey(student1.getId(), course1.getId()),student1,course1);
        StudentToCourse studentToCourse2 = new StudentToCourse(new StudentToCourseCompositeKey(student2.getId(), course2.getId()),student2,course2);
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->studentToCourseService.addStudentToCourse(studentToCourse1));
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->studentToCourseService.addStudentToCourse(studentToCourse2));
    }
}
