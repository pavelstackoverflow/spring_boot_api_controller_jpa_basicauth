package com.petprojects.webapi.repository;

import com.petprojects.webapi.entity.StudentToCourse;
import com.petprojects.webapi.entity.StudentToCourseCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StudentToCourseRepository extends JpaRepository<StudentToCourse, StudentToCourseCompositeKey> {

    @Modifying
    @Transactional
    @Query("update StudentToCourse s set s.mark = ?1 where s.student.id = ?2 and s.course.id= ?3")
    void setMark(Integer mark, Long studentId, Long courseId);
}
