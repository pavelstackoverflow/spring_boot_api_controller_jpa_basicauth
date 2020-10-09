package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.StudentDTO;
import com.petprojects.webapi.dto.StudentSimpleDTO;
import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.Teacher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.NoResultException;
import java.util.List;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    public void crudTest() {
        Assertions.assertThat(studentService.getStudentList()).isNotNull()
                .hasSize(2);
        Student student = new Student("Test","Student");
        studentService.addStudent(student);
        Assertions.assertThat(studentService.getStudentList()).isNotNull()
                .hasSize(3);
        Assertions.assertThat(studentService.getStudentListByName("est")).isNotNull()
                .hasSize(1);
        student = new Student(3L, "ChangeTest","ChangeLastName");
        studentService.editStudent(student);
        Assertions.assertThat(studentService.getStudentListByName("hange")).isNotNull()
                .hasSize(1);
        Teacher teacher = new Teacher(1L);
        studentService.addStudentTeacher(student.getId(), teacher.getId());
        studentService.deleteStudent(student.getId());
        Assertions.assertThat(studentService.getStudentList()).isNotNull()
                .hasSize(2);

    }



    @Test
    public void checkStudentListFormat() {
        List<StudentSimpleDTO> studentSimpleDTOList = studentService.getStudentList();
        for (int i = 0; i < studentSimpleDTOList.size(); i++) {
            Assertions.assertThat(studentSimpleDTOList.get(i).getId()).isNotNull();
            Assertions.assertThat(studentSimpleDTOList.get(i).getFirstName()).isNotNull();
            Assertions.assertThat(studentSimpleDTOList.get(i).getLastName()).isNotNull();
        }
    }

    @Test
    public void checkStudentChild() {
        StudentDTO studentDTO = studentService.getStudent(1L);
        System.out.println(studentDTO);
        Assertions.assertThat(studentDTO).isNotNull();
        Assertions.assertThat(studentDTO.getFirstName()).isNotNull();
        Assertions.assertThat(studentDTO.getCourses()).isNotNull();
        Assertions.assertThat(studentDTO.getCourses()).hasSize(2);
        Assertions.assertThat(studentDTO.getTeachers()).isNotNull();
        Assertions.assertThat(studentDTO.getTeachers()).hasSize(2);
        Assertions.assertThatExceptionOfType(NoResultException.class).isThrownBy(()->studentService.getStudent(5L));
    }
}
