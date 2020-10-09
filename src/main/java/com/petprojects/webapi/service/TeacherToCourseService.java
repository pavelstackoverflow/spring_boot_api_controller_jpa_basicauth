package com.petprojects.webapi.service;

import com.petprojects.webapi.entity.TeacherToCourse;
import com.petprojects.webapi.repository.TeacherToCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherToCourseService {

    @Autowired
    TeacherToCourseRepository teacherToCourseRepository;

    public void addTeacherToCourse(TeacherToCourse teacherToCourse) {
        teacherToCourseRepository.saveAndFlush(teacherToCourse);
    }

    public void deleteTeacherFromCourse(TeacherToCourse teacherToCourse) {
        teacherToCourseRepository.delete(teacherToCourse);
    }
}
