package com.petprojects.webapi.controller;

import com.petprojects.webapi.dto.QuestionDTO;
import com.petprojects.webapi.entity.Question;
import com.petprojects.webapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService = new QuestionService();

    @CrossOrigin
    @GetMapping("/api/public/getStudentQuestions/{id}")
    public Set<QuestionDTO> getStudentQuestions(HttpServletRequest request, @PathVariable Long id) {
        return questionService.getStudentQuestions(id);
    }

    @CrossOrigin
    @GetMapping("/api/public/getTeacherQuestions/{id}")
    public Set<QuestionDTO> getTeacherQuestions(HttpServletRequest request, @PathVariable Long id) {
        return questionService.getTeacherQuestions(id);
    }

    @CrossOrigin
    @PostMapping("/api/private/addQuestion")
    public void addQuestion(HttpServletRequest request, @RequestBody Question question) {
        questionService.addQuestion(question);
    }

    @CrossOrigin
    @DeleteMapping("/api/private/deleteQuestion")
    public void deleteQuestion(HttpServletRequest request, @RequestBody Question question) {
        questionService.deleteQuestion(question);
    }
}
