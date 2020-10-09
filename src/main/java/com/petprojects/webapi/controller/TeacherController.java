package com.petprojects.webapi.controller;

import com.petprojects.webapi.dto.TeacherDTO;
import com.petprojects.webapi.dto.TeacherSimpleDTO;
import com.petprojects.webapi.entity.Teacher;
import com.petprojects.webapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService = new TeacherService();

    @CrossOrigin
    @GetMapping("/api/public/getTeacherList")
    public List<TeacherSimpleDTO> getTeacherList(HttpServletRequest request) {
        return teacherService.getTeacherList();
    }

    @CrossOrigin
    @GetMapping("/api/public/getTeacherListByName")
    public List<TeacherSimpleDTO> getTeacherListByName(HttpServletRequest request,
                                                       @RequestParam(required =true, name = "name") String name) {
        return teacherService.getTeacherListByName(name);
    }

    @CrossOrigin
    @GetMapping("/api/public/getTeacher/{id}")
    public TeacherDTO getTeacher(HttpServletRequest request, @PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @CrossOrigin
    @PutMapping("/api/private/editTeacher")
    public void editTeacher(HttpServletRequest request, @RequestBody Teacher teacher) {
        teacherService.editTeacher(teacher);
    }


    @CrossOrigin
    @PostMapping("/api/private/addTeacher")
    public void addTeacher(HttpServletRequest request, @RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteTeacher/{id}")
    public void deleteTeacher(HttpServletRequest request, @PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

}
