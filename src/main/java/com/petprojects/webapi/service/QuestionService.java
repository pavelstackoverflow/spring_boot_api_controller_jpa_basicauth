package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.QuestionDTO;
import com.petprojects.webapi.dto.StudentSimpleDTO;
import com.petprojects.webapi.dto.TeacherSimpleDTO;
import com.petprojects.webapi.entity.Question;
import com.petprojects.webapi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Set<QuestionDTO> getStudentQuestions(Long studentId) {
        TypedQuery<Question> query = entityManager.createQuery("SELECT q from Question q " +
                "JOIN FETCH q.student s " +
                "JOIN FETCH q.teacher t " +
                "WHERE q.student.id=:studentId", Question.class).setParameter("studentId", studentId);
        List<Question> studentQuestions = query.getResultList();
        Set<QuestionDTO> studentQuestionsDTO = new HashSet<>();
        for (Question question : studentQuestions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.convert(question);
            TeacherSimpleDTO teacherSimpleDTO = new TeacherSimpleDTO();
            teacherSimpleDTO.convert(question.getTeacher());
            questionDTO.setTeacher(teacherSimpleDTO);
            studentQuestionsDTO.add(questionDTO);
        }
        return studentQuestionsDTO;
    }

    @Transactional
    public Set<QuestionDTO> getTeacherQuestions(Long teacherId) {
        TypedQuery<Question> query = entityManager.createQuery("SELECT q from Question q " +
                "JOIN FETCH q.student s " +
                "JOIN FETCH q.teacher t " +
                "WHERE q.teacher.id=:teacherId", Question.class).setParameter("teacherId", teacherId);
        List<Question> teacherQuestions = query.getResultList();
        Set<QuestionDTO> teacherQuestionsDTO = new HashSet<>();
        for (Question question : teacherQuestions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.convert(question);
            StudentSimpleDTO studentSimpleDTO = new StudentSimpleDTO();
            studentSimpleDTO.convert(question.getStudent());
            questionDTO.setStudent(studentSimpleDTO);
            teacherQuestionsDTO.add(questionDTO);
        }
        return teacherQuestionsDTO;
    }


    public void addQuestion(Question question) {
        questionRepository.saveAndFlush(question);
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }


}
