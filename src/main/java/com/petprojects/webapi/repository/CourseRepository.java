package com.petprojects.webapi.repository;

import com.petprojects.webapi.dto.CourseDTO;
import com.petprojects.webapi.dto.CourseSimpleDTO;
import com.petprojects.webapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Transactional
    @Query("update Course c set c.courseName = ?1 where c.id = ?2")
    void editCourse(String courseName, Long courseId);

    @Query("select new com.petprojects.webapi.dto.CourseSimpleDTO(c.id, c.courseName) from Course c where c.courseName LIKE %?1%")
    List<CourseSimpleDTO> getCourseByName(String courseName);

    @Query("select new com.petprojects.webapi.dto.CourseSimpleDTO(c.id, c.courseName) from Course c")
    List<CourseSimpleDTO> getCourseList();

}
