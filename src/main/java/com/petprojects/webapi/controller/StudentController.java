package com.petprojects.webapi.controller;

import com.petprojects.webapi.dto.StudentDTO;
import com.petprojects.webapi.dto.StudentSimpleDTO;
import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService = new StudentService();

    @CrossOrigin
    @GetMapping("/api/public/getStudentList")
    public List<StudentSimpleDTO> getStudentList(HttpServletRequest request) {
        return studentService.getStudentList();
    }

    @CrossOrigin
    @GetMapping("/api/public/getStudentListByName")
    public List<StudentSimpleDTO> getStudentListByName(HttpServletRequest request,
                                                       @RequestParam(required =true, name = "name") String name) {
        return studentService.getStudentListByName(name);
    }

    @CrossOrigin
    @GetMapping("/api/public/getStudent/{id}")
    public StudentDTO getStudent(HttpServletRequest request, @PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @CrossOrigin
    @PutMapping("/api/private/editStudent")
    public void editStudent(HttpServletRequest request, @RequestBody Student student) {
        studentService.editStudent(student);
    }


    @CrossOrigin
    @PostMapping("/api/private/addStudent")
    public void addStudent(HttpServletRequest request, @RequestBody Student student) {
        studentService.addStudent(student);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteStudent/{id}")
    public void deleteStudent(HttpServletRequest request, @PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @CrossOrigin
    @PostMapping("/api/private/addStudentTeacher/{id}")
    public void addStudentTeacher(HttpServletRequest request, @RequestBody Student student, @PathVariable Long id) {
        studentService.addStudentTeacher(student.getId(), id);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteStudentTeacher/{id}")
    public void deleteStudentTeacher(HttpServletRequest request, @RequestBody Student student, @PathVariable Long id) {
        studentService.deleteStudentTeacher(student.getId(), id);
    }
}
