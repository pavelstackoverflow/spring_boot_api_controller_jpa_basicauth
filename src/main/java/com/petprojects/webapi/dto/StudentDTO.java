package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Student;

import java.util.Set;

public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;

    private Set<TeacherSimpleDTO> teachers;

    private Set<StudentToCourseDTO> courses;

    private Set<TaskDTO> tasks;

    private Set<QuestionDTO> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<TeacherSimpleDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherSimpleDTO> teachers) {
        this.teachers = teachers;
    }

    public Set<StudentToCourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<StudentToCourseDTO> courses) {
        this.courses = courses;
    }

    public Set<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public Set<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDTO> questions) {
        this.questions = questions;
    }

    public StudentDTO(Long id, String firstName, String lastName, Set<TeacherSimpleDTO> teachers, Set<StudentToCourseDTO> courses, Set<TaskDTO> tasks,
                      Set<QuestionDTO> questions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teachers = teachers;
        this.courses = courses;
        this.tasks = tasks;
        this.questions = questions;
    }

    public StudentDTO() {
    }

    public StudentDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void convert(Student student) {
        this.id  = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
    }

    @Override
    public String toString() {
        String result = "Student{";
        if (id!=null) {
            result+="id=" + id;
        } else {
            result+="id=null";
        }
        if (firstName!=null) {
            result+=",firstName=" + firstName;
        } else {
            result+=",firstName=null";
        }
        if (lastName!=null) {
            result+=",lastName=" + lastName;
        } else {
            result+=",lastName=null";
        }
        if (teachers!=null) {
            if (teachers.size()>0) {
                result+=",teachers=[";
                StringBuilder stringBuilder = new StringBuilder();
                for (TeacherSimpleDTO teacher : this.teachers){
                    stringBuilder.append(teacher.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + "]";
            } else {
                result+=",teachers=0 elements";
            }
        } else {
            result+=",teachers=null";
        }
        if (courses!=null) {
            if (courses.size()>0) {
                result+=",courses=[";
                StringBuilder stringBuilder = new StringBuilder();
                for (StudentToCourseDTO course : this.courses){
                    stringBuilder.append(course.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + "]";
            } else {
                result+=",courses=0 elements";
            }
        } else {
            result+=",courses=null";
        }
        if (tasks!=null) {
            if (tasks.size()>0) {
                result+=",tasks=[";
                StringBuilder stringBuilder = new StringBuilder();
                for (TaskDTO task : this.tasks){
                    stringBuilder.append(task.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + "]";
            } else {
                result+=",tasks=0 elements";
            }
        } else {
            result+=",tasks=null";
        }
        if (questions!=null) {
            if (questions.size()>0) {
                result+=",questions=[";
                StringBuilder stringBuilder = new StringBuilder();
                for (QuestionDTO question : this.questions){
                    stringBuilder.append(question.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + "]";
            } else {
                result+=",questions=0 elements";
            }
        } else {
            result+=",questions=null";
        }
        result+=")";

        return result;
    }
}
