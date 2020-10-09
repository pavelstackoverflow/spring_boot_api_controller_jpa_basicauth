package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.CourseSimpleDTO;
import com.petprojects.webapi.dto.StudentSimpleDTO;
import com.petprojects.webapi.dto.TeacherDTO;
import com.petprojects.webapi.dto.TeacherSimpleDTO;
import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.Task;
import com.petprojects.webapi.entity.Teacher;
import com.petprojects.webapi.entity.TeacherToCourse;
import com.petprojects.webapi.repository.TaskRepository;
import com.petprojects.webapi.repository.TeacherRepository;
import com.petprojects.webapi.repository.TeacherToCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public TeacherDTO getTeacher(Long teacherId) {
        Teacher teacherWithStudents = (Teacher) entityManager.createQuery("SELECT t from Teacher t " +
                "LEFT JOIN FETCH t.students ts " +
                "WHERE t.id=:teacherId").setParameter("teacherId", teacherId).getSingleResult();
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.convert(teacherWithStudents);
        Set<StudentSimpleDTO> studentSimpleDTOS = new HashSet<>();
        for (Student student : teacherWithStudents.getStudents()) {
            StudentSimpleDTO studentSimpleDTO = new StudentSimpleDTO();
            studentSimpleDTO.convert(student);
            studentSimpleDTOS.add(studentSimpleDTO);
        }
        teacherDTO.setStudents(studentSimpleDTOS);

        Teacher teacherWithCourses = (Teacher) entityManager.createQuery("SELECT t from Teacher t " +
                "LEFT JOIN FETCH t.courses tc " +
                "LEFT JOIN FETCH tc.course c " +
                "WHERE t.id=:teacherId").setParameter("teacherId", teacherId).getSingleResult();
        Set<CourseSimpleDTO> courseSimpleDTOS = new HashSet<>();
        for (TeacherToCourse teacherToCourse : teacherWithCourses.getCourses()) {
            CourseSimpleDTO courseSimpleDTO = new CourseSimpleDTO();
            courseSimpleDTO.convert(teacherToCourse.getCourse());
            courseSimpleDTOS.add(courseSimpleDTO);
        }
        teacherDTO.setCourses(courseSimpleDTOS);
        return teacherDTO;
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.saveAndFlush(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    public void editTeacher(Teacher teacher) {
        teacherRepository.editTeacher(teacher.getFirstName(), teacher.getFirstName(), teacher.getId());
    }

    public List<TeacherSimpleDTO> getTeacherList() {
        return teacherRepository.getTeacherList();
    }

    public List<TeacherSimpleDTO> getTeacherListByName(String name) {
        return teacherRepository.getTeacherByName(name);
    }


}
