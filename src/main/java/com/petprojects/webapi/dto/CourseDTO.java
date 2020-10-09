package com.petprojects.webapi.dto;

import com.petprojects.webapi.entity.Course;

import java.util.Set;

public class CourseDTO {

    private Long id;
    private String courseName;

    private Set<TeacherSimpleDTO> teachers;

    private Set<StudentToCourseDTO> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Set<TeacherSimpleDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherSimpleDTO> teachers) {
        this.teachers = teachers;
    }

    public Set<StudentToCourseDTO> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentToCourseDTO> students) {
        this.students = students;
    }

    public CourseDTO(Long id, String courseName, Set<TeacherSimpleDTO> teachers, Set<StudentToCourseDTO> students) {
        this.id = id;
        this.courseName = courseName;
        this.teachers = teachers;
        this.students = students;
    }

    public CourseDTO() {
    }

    public CourseDTO(Long id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public void convert(Course course) {
        this.id  = course.getId();
        this.courseName  = course.getCourseName();
    }

    @Override
    public String toString() {
        String result = "Course{";
        if (id!=null) {
            result+="id=" + id;
        } else {
            result+="id=null";
        }
        if (courseName!=null) {
            result+=",courseName=" + courseName;
        } else {
            result+=",courseName=null";
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
        if (students!=null) {
            if (students.size()>0) {
                result+=",students=[";
                StringBuilder stringBuilder = new StringBuilder();
                for (StudentToCourseDTO student : this.students){
                    stringBuilder.append(student.toString());
                    stringBuilder.append(" ");
                }
                result+=stringBuilder.toString() + "]";
            } else {
                result+=",students=0 elements";
            }

        } else {
            result+=",students=null";
        }
        result+=")";
        return result;
    }
}
