package com.petprojects.webapi.controller;

import com.petprojects.webapi.dto.TaskDTO;
import com.petprojects.webapi.entity.Task;
import com.petprojects.webapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService = new TaskService();

    @CrossOrigin
    @GetMapping("/api/public/getStudentTask/{id}")
    public Set<TaskDTO> getStudentTask(HttpServletRequest request, @PathVariable Long id) {
        return taskService.getStudentTask(id);
    }

    @CrossOrigin
    @PostMapping("/api/private/addTask")
    public void addTask(HttpServletRequest request, @RequestBody Task task) {
        taskService.addTask(task);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteTask")
    public void deleteTask(HttpServletRequest request, @RequestBody Task task) {
        taskService.deleteTask(task);
    }
}
