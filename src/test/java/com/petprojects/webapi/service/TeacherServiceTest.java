package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.TeacherDTO;
import com.petprojects.webapi.dto.TeacherSimpleDTO;
import com.petprojects.webapi.entity.Teacher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.NoResultException;
import java.util.List;

@SpringBootTest
public class TeacherServiceTest {

    @Autowired
    TeacherService teacherService;

    @Test
    public void crudTest() {
        Assertions.assertThat(teacherService.getTeacherList()).isNotNull()
                .hasSize(2);
        Teacher teacher = new Teacher("Test","Teacher");
        teacherService.addTeacher(teacher);
        Assertions.assertThat(teacherService.getTeacherList()).isNotNull()
                .hasSize(3);
        Assertions.assertThat(teacherService.getTeacherListByName("Test")).isNotNull()
                .hasSize(1);
        teacher = new Teacher(3L, "Change","ChangeTeacher");
        teacherService.editTeacher(teacher);
        Assertions.assertThat(teacherService.getTeacherListByName("Change")).isNotNull()
                .hasSize(1);
        teacherService.deleteTeacher(teacher.getId());
        Assertions.assertThat(teacherService.getTeacherList()).isNotNull()
                .hasSize(2);

    }

    @Test
    public void checkCourseListFormat() {
        List<TeacherSimpleDTO> teacherList = teacherService.getTeacherList();
        for (int i = 0; i < teacherList.size(); i++) {
            Assertions.assertThat(teacherList.get(i).getId()).isNotNull();
            Assertions.assertThat(teacherList.get(i).getFirstName()).isNotNull();
        }
    }

    @Test
    public void checkCourseChild() {
        TeacherDTO teacherDTO = teacherService.getTeacher(1L);
        System.out.println(teacherDTO);
        Assertions.assertThat(teacherDTO).isNotNull();
        Assertions.assertThat(teacherDTO.getStudents()).isNotNull();
        Assertions.assertThat(teacherDTO.getStudents()).hasSize(1);
        Assertions.assertThat(teacherDTO.getCourses()).isNotNull();
        Assertions.assertThat(teacherDTO.getCourses()).hasSize(1);
        Assertions.assertThatExceptionOfType(NoResultException.class).isThrownBy(()->teacherService.getTeacher(5L));
    }
}
