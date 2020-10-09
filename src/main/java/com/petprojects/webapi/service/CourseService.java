package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.*;
import com.petprojects.webapi.entity.Course;
import com.petprojects.webapi.entity.StudentToCourse;
import com.petprojects.webapi.entity.TeacherToCourse;
import com.petprojects.webapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CourseDTO getCourse(Long courseId) {
        Course courseWithStudents = (Course) entityManager.createQuery("SELECT c from Course c " +
                "LEFT JOIN FETCH c.students cs " +
                "LEFT JOIN FETCH cs.student s " +
                "WHERE c.id=:courseId").setParameter("courseId", courseId).getSingleResult();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.convert(courseWithStudents);
        Set<StudentToCourseDTO> studentToCourseDTOS = new HashSet<>();
        for (StudentToCourse studentToCourse : courseWithStudents.getStudents()) {
            StudentToCourseDTO studentToCourseDTO = new StudentToCourseDTO();
            studentToCourseDTO.convert(studentToCourse);
            StudentSimpleDTO studentSimpleDTO = new StudentSimpleDTO();
            studentSimpleDTO.convert(studentToCourse.getStudent());
            studentToCourseDTO.setStudent(studentSimpleDTO);
            studentToCourseDTOS.add(studentToCourseDTO);
        }
        courseDTO.setStudents(studentToCourseDTOS);

        Course courseWithTeachers = (Course) entityManager.createQuery("SELECT c from Course c " +
                "LEFT JOIN FETCH c.teachers ct " +
                "LEFT JOIN FETCH ct.teacher t " +
                "WHERE c.id=:courseId").setParameter("courseId", courseId).getSingleResult();
        Set<TeacherSimpleDTO> teacherSimpleDTOS = new HashSet<>();
        for (TeacherToCourse teacherToCourse : courseWithTeachers.getTeachers()) {
            TeacherSimpleDTO teacherSimpleDTO = new TeacherSimpleDTO();
            teacherSimpleDTO.convert(teacherToCourse.getTeacher());
            teacherSimpleDTOS.add(teacherSimpleDTO);
        }
        courseDTO.setTeachers(teacherSimpleDTOS);
        return courseDTO;
    }


    public void addCourse(Course course) {
        courseRepository.saveAndFlush(course);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public void editCourse(Course course) {
        courseRepository.editCourse(course.getCourseName(), course.getId());
    }

    public List<CourseSimpleDTO> getCourseList() {
        return courseRepository.getCourseList();
    }

    public List<CourseSimpleDTO> getCourseListByName(String courseName) {
        return courseRepository.getCourseByName(courseName);
    }


}
