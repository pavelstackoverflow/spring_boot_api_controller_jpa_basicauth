package com.petprojects.webapi.controller;

import com.petprojects.webapi.dto.CourseDTO;
import com.petprojects.webapi.dto.CourseSimpleDTO;
import com.petprojects.webapi.entity.Course;
import com.petprojects.webapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService = new CourseService();

    @CrossOrigin
    @GetMapping("/api/public/getCourseList")
    public List<CourseSimpleDTO> getCourseList(HttpServletRequest request) {
        return courseService.getCourseList();
    }

    @CrossOrigin
    @GetMapping("/api/public/getCourseListByName")
    public List<CourseSimpleDTO> getCourseListByName(HttpServletRequest request,
                                                       @RequestParam(required =true, name = "name") String name) {
        return courseService.getCourseListByName(name);
    }

    @CrossOrigin
    @GetMapping("/api/public/getCourse/{id}")
    public CourseDTO getCourse(HttpServletRequest request, @PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @CrossOrigin
    @PutMapping("/api/private/editCourse")
    public void editCourse(HttpServletRequest request, @RequestBody Course course) {
        courseService.editCourse(course);
    }


    @CrossOrigin
    @PostMapping("/api/private/addCourse")
    public void addCourse(HttpServletRequest request, @RequestBody Course course) {
        courseService.addCourse(course);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteCourse/{id}")
    public void deleteCourse(HttpServletRequest request, @PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
