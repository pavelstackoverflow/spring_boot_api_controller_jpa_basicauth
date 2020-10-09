package com.petprojects.webapi.repository;

import com.petprojects.webapi.entity.TeacherToCourse;
import com.petprojects.webapi.entity.TeacherToCourseCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherToCourseRepository extends JpaRepository<TeacherToCourse, TeacherToCourseCompositeKey> {
}
