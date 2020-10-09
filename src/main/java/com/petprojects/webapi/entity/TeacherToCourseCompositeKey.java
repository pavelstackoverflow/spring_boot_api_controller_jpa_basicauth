package com.petprojects.webapi.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TeacherToCourseCompositeKey implements Serializable {

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "course_id")
    private Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherToCourseCompositeKey that = (TeacherToCourseCompositeKey) o;

        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        return courseId != null ? courseId.equals(that.courseId) : that.courseId == null;
    }

    @Override
    public int hashCode() {
        int result = teacherId != null ? teacherId.hashCode() : 0;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        return result;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public TeacherToCourseCompositeKey(Long teacherId, Long courseId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public TeacherToCourseCompositeKey() {
    }
}
