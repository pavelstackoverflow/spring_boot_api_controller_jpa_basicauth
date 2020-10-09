package com.petprojects.webapi.Demo;

import com.petprojects.webapi.entity.*;
import com.petprojects.webapi.entity.security.ApplicationUser;
import com.petprojects.webapi.entity.security.ApplicationUserPermission;
import com.petprojects.webapi.repository.StudentRepository;
import com.petprojects.webapi.repository.TeacherRepository;
import com.petprojects.webapi.service.*;
import com.petprojects.webapi.service.security.ApplicationPermissionService;
import com.petprojects.webapi.service.security.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DemoStudentTeacher implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentToCourseService studentToCourseService;

    @Autowired
    private TeacherToCourseService teacherToCourseService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ApplicationPermissionService applicationPermissionService;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student("Student1", "Ivanov");
        Student student2 = new Student("Student2", "Petrov");

        Teacher teacher1 = new Teacher("Teacher1", "T1");
        Teacher teacher2 = new Teacher("Teacher2", "T2");

        Course course1 = new Course("Java");
        Course course2 = new Course("Web");

        studentService.addStudent(student1);
        studentService.addStudent(student2);

        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);

        courseService.addCourse(course1);
        courseService.addCourse(course2);

        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        student1.setTeachers(teachers);

        studentService.addStudent(student1);

        StudentToCourse studentToCourse1 = new StudentToCourse(new StudentToCourseCompositeKey(student1.getId(), course1.getId()),
                student1, course1, 5);

        StudentToCourse studentToCourse2 = new StudentToCourse(new StudentToCourseCompositeKey(student1.getId(), course2.getId()),
                student1, course2, 4);

        studentToCourseService.addStudentToCourse(studentToCourse1);
        studentToCourseService.addStudentToCourse(studentToCourse2);

        TeacherToCourse teacherToCourse1 = new TeacherToCourse(new TeacherToCourseCompositeKey(teacher1.getId(), course1.getId()),
                teacher1, course1);

        TeacherToCourse teacherToCourse2 = new TeacherToCourse(new TeacherToCourseCompositeKey(teacher2.getId(), course1.getId()),
                teacher2, course1);

        teacherToCourseService.addTeacherToCourse(teacherToCourse1);
        teacherToCourseService.addTeacherToCourse(teacherToCourse2);

        Task task1 = new Task("Task1", student1, course1);
        Task task2 = new Task("Task2", student1, course1);
        Task task3 = new Task("Task3", student1, course1);

        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);

        Question question1 = new Question("Question1", student2, teacher1);
        Question question2 = new Question("Question2", student2, teacher1);
        Question question3 = new Question("Question3", student2, teacher2);

        questionService.addQuestion(question1);
        questionService.addQuestion(question2);
        questionService.addQuestion(question3);

        ApplicationUserPermission applicationUserPermission1 = new ApplicationUserPermission("admin");
        ApplicationUserPermission applicationUserPermission2 = new ApplicationUserPermission("user");

        applicationPermissionService.addPermission(applicationUserPermission1);
        applicationPermissionService.addPermission(applicationUserPermission2);

        ApplicationUser applicationUser1 = new ApplicationUser("admin","12345","testmail");
        ApplicationUser applicationUser2 = new ApplicationUser("user","12345","testmail");

        applicationUserService.addUser(applicationUser1);
        applicationUserService.addUser(applicationUser2);


    }

}
