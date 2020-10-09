package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Task;

public class TaskDTO {

    private Long id;
    private String taskName;

    private StudentSimpleDTO student;

    private CourseSimpleDTO course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public StudentSimpleDTO getStudent() {
        return student;
    }

    public void setStudent(StudentSimpleDTO student) {
        this.student = student;
    }

    public CourseSimpleDTO getCourse() {
        return course;
    }

    public void setCourse(CourseSimpleDTO course) {
        this.course = course;
    }

    public TaskDTO(Long id, String taskName, StudentSimpleDTO student, CourseSimpleDTO course) {
        this.id = id;
        this.taskName = taskName;
        this.student = student;
        this.course = course;
    }

    public TaskDTO() {
    }

    public void convert(Task task) {
        this.id  = task.getId();
        this.taskName = task.getTaskName();
    }

    @Override
    public String toString() {
        String result = "Task{";
        if (id!=null) {
            result+="id=" + id;
        } else {
            result+="id=null";
        }
        if (taskName!=null) {
            result+=",taskName=" + taskName;
        } else {
            result+=",taskName=null";
        }
        if (student!=null) {
            result+="," + student.toString();
        } else {
            result+=",student=null";
        }
        if (course!=null) {
            result+="," + course.toString();
        } else {
            result+=",course=null";
        }
        result+=")";
        return result;
    }
}
