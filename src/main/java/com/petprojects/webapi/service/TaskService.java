package com.petprojects.webapi.service;

import com.petprojects.webapi.dto.CourseSimpleDTO;
import com.petprojects.webapi.dto.TaskDTO;
import com.petprojects.webapi.entity.Task;
import com.petprojects.webapi.repository.TaskRepository;
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
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Set<TaskDTO> getStudentTask(Long studentId) {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t from Task t " +
                "JOIN FETCH t.student s " +
                "JOIN FETCH t.course c " +
                "WHERE t.student.id=:studentId", Task.class).setParameter("studentId", studentId);
        List<Task> studentTasks = query.getResultList();
        Set<TaskDTO> studentTasksDTO = new HashSet<>();
        for (Task task : studentTasks) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.convert(task);
            CourseSimpleDTO courseSimpleDTO = new CourseSimpleDTO();
            courseSimpleDTO.convert(task.getCourse());
            taskDTO.setCourse(courseSimpleDTO);
            studentTasksDTO.add(taskDTO);
        }
        return studentTasksDTO;
    }

    public void addTask(Task task) {
        taskRepository.saveAndFlush(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

}
