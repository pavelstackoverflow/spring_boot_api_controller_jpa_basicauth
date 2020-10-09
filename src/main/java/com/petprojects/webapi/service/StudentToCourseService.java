package com.petprojects.webapi.service;

import com.petprojects.webapi.entity.StudentToCourse;
import com.petprojects.webapi.repository.StudentToCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentToCourseService {

    @Autowired
    StudentToCourseRepository studentToCourseRepository;

    public void addStudentToCourse(StudentToCourse studentToCourse) {
        studentToCourseRepository.saveAndFlush(studentToCourse);
    }

    public void deleteStudentFromCourse(StudentToCourse studentToCourse) {
        studentToCourseRepository.delete(studentToCourse);
    }

    public void setMark(StudentToCourse studentToCourse) {
        studentToCourseRepository.setMark(studentToCourse.getMark(), studentToCourse.getStudent().getId(), studentToCourse.getCourse().getId());
    }
}
