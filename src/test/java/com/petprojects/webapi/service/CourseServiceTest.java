package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.CourseDTO;
import com.petprojects.webapi.dto.CourseSimpleDTO;
import com.petprojects.webapi.entity.Course;
import com.petprojects.webapi.repository.CourseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import javax.persistence.NoResultException;
import java.util.List;

@SpringBootTest
public class CourseServiceTest {


    @Autowired
    CourseService courseService;

    @Test
    public void crudTest() {
        Assertions.assertThat(courseService.getCourseList()).isNotNull()
                .hasSize(2);
        Course course1 = new Course("Math");
        courseService.addCourse(course1);
        Assertions.assertThat(courseService.getCourseList()).isNotNull()
                .hasSize(3);
        Assertions.assertThat(courseService.getCourseListByName("Mat")).isNotNull()
                .hasSize(1);
        course1 = new Course(3L, "Python");
        courseService.editCourse(course1);
        Assertions.assertThat(courseService.getCourseListByName("Pyt")).isNotNull()
                .hasSize(1);
        courseService.deleteCourse(course1.getId());
        Assertions.assertThat(courseService.getCourseList()).isNotNull()
                .hasSize(2);

    }

    @Test
    public void checkCourseListFormat() {
        List<CourseSimpleDTO> courseList = courseService.getCourseList();
        for (int i = 0; i < courseList.size(); i++) {
            Assertions.assertThat(courseList.get(i).getId()).isNotNull();
            Assertions.assertThat(courseList.get(i).getCourseName()).isNotNull();
        }
    }

    @Test
    public void checkCourseChild() {
        CourseDTO course = courseService.getCourse(1L);
        System.out.println(course);
        Assertions.assertThat(course).isNotNull();
        Assertions.assertThat(course.getStudents()).isNotNull();
        Assertions.assertThat(course.getStudents()).hasSize(1);
        Assertions.assertThat(course.getTeachers()).isNotNull();
        Assertions.assertThat(course.getTeachers()).hasSize(2);
        Assertions.assertThatExceptionOfType(NoResultException.class).isThrownBy(()->courseService.getCourse(5L));
    }

}
