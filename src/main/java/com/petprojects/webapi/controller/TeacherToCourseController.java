package com.petprojects.webapi.controller;

import com.petprojects.webapi.entity.TeacherToCourse;
import com.petprojects.webapi.service.TeacherToCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TeacherToCourseController {

    @Autowired
    TeacherToCourseService teacherToCourseService = new TeacherToCourseService();

    @CrossOrigin
    @PostMapping("/api/private/addTeacherToCourse")
    public void addTeacherToCourse(HttpServletRequest request, @RequestBody TeacherToCourse teacherToCourse) {
        teacherToCourseService.addTeacherToCourse(teacherToCourse);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteTeacherToCourse")
    public void deleteTeacherFromCourse(HttpServletRequest request, @RequestBody TeacherToCourse teacherToCourse) {
        teacherToCourseService.deleteTeacherFromCourse(teacherToCourse);
    }


}
