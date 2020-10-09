package com.petprojects.webapi.repository;

import com.petprojects.webapi.dto.TeacherSimpleDTO;
import com.petprojects.webapi.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Modifying
    @Transactional
    @Query("update Teacher t set t.firstName = ?1, t.lastName = ?2 where t.id = ?3")
    void editTeacher(String firstName, String lastName, Long teacherId);

    @Query("select new com.petprojects.webapi.dto.TeacherSimpleDTO(t.id, t.firstName, t.lastName) from Teacher t " +
            "where t.firstName LIKE %?1% OR t.lastName LIKE %?1%")
    List<TeacherSimpleDTO> getTeacherByName(String name);

    @Query("select new com.petprojects.webapi.dto.TeacherSimpleDTO(t.id, t.firstName, t.lastName) from Teacher t")
    List<TeacherSimpleDTO> getTeacherList();

}
