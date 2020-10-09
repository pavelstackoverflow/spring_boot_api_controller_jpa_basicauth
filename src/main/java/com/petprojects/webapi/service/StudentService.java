package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.*;
import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.StudentToCourse;
import com.petprojects.webapi.entity.Teacher;
import com.petprojects.webapi.entity.TeacherToCourse;
import com.petprojects.webapi.repository.StudentRepository;
import com.petprojects.webapi.repository.StudentToCourseRepository;
import com.petprojects.webapi.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public StudentDTO getStudent(Long studentId) {
        Student studentWithCourses = (Student) entityManager.createQuery("SELECT s from Student s " +
                "LEFT JOIN FETCH s.courses sc " +
                "LEFT JOIN FETCH sc.course c " +
                "WHERE s.id=:studentId").setParameter("studentId", studentId).getSingleResult();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.convert(studentWithCourses);
        Set<StudentToCourseDTO> studentToCourseDTOS = new HashSet<>();
        for (StudentToCourse studentToCourse : studentWithCourses.getCourses()) {
            StudentToCourseDTO studentToCourseDTO = new StudentToCourseDTO();
            studentToCourseDTO.convert(studentToCourse);
            CourseSimpleDTO courseSimpleDTO = new CourseSimpleDTO();
            courseSimpleDTO.convert(studentToCourse.getCourse());
            studentToCourseDTO.setCourse(courseSimpleDTO);
            studentToCourseDTOS.add(studentToCourseDTO);
        }
        studentDTO.setCourses(studentToCourseDTOS);

        Student studentWithTeachers = (Student) entityManager.createQuery("SELECT s from Student s " +
                "LEFT JOIN FETCH s.teachers t " +
                "WHERE s.id=:studentId").setParameter("studentId", studentId).getSingleResult();
        Set<TeacherSimpleDTO> teacherSimpleDTOS = new HashSet<>();
        for (Teacher teacher : studentWithTeachers.getTeachers()) {
            TeacherSimpleDTO teacherSimpleDTO = new TeacherSimpleDTO();
            teacherSimpleDTO.convert(teacher);
            teacherSimpleDTOS.add(teacherSimpleDTO);
        }
        studentDTO.setTeachers(teacherSimpleDTOS);
        return studentDTO;
    }

    public void addStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void editStudent(Student student) {
        studentRepository.editStudent(student.getFirstName(), student.getFirstName(), student.getId());
    }

    public List<StudentSimpleDTO> getStudentList() {
        return studentRepository.getStudentList();
    }

    public List<StudentSimpleDTO> getStudentListByName(String name) {
        return studentRepository.getStudentByName(name);
    }

    public void addStudentTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.getStudentById(studentId);
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        if (student!=null && teacher!=null) {
            student.addTeacher(teacher);
            studentRepository.save(student);
        }
    }

    public void deleteStudentTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.getStudentById(studentId);
        if (student!=null) {
            student.deleteTeacher(teacherId);
            studentRepository.save(student);
        }
    }



}
