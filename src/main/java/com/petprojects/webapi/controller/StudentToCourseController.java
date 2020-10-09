package com.petprojects.webapi.controller;

import com.petprojects.webapi.entity.StudentToCourse;
import com.petprojects.webapi.service.StudentToCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentToCourseController {

    @Autowired
    StudentToCourseService studentToCourseService = new StudentToCourseService();

    @CrossOrigin
    @PostMapping("/api/private/addStudentToCourse")
    public void addStudentToCourse(HttpServletRequest request, @RequestBody StudentToCourse studentToCourse) {
        studentToCourseService.addStudentToCourse(studentToCourse);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteStudentFromCourse")
    public void deleteStudentFromCourse(HttpServletRequest request, @RequestBody StudentToCourse studentToCourse) {
        studentToCourseService.deleteStudentFromCourse(studentToCourse);
    }

    @CrossOrigin
    @PutMapping("/api/private/setMark")
    public void setMark(HttpServletRequest request, @RequestBody StudentToCourse studentToCourse) {
        studentToCourseService.setMark(studentToCourse);
    }
}
