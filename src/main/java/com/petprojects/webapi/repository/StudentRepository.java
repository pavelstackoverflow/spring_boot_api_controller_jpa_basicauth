package com.petprojects.webapi.repository;

import com.petprojects.webapi.dto.StudentSimpleDTO;
import com.petprojects.webapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Transactional
    @Query("update Student s set s.firstName = ?1, s.lastName = ?2 where s.id = ?3")
    void editStudent(String firstName, String lastName, Long userId);

    @Query("select new com.petprojects.webapi.dto.StudentSimpleDTO(s.id, s.firstName, s.lastName) from Student s")
    List<StudentSimpleDTO> getStudentList();

    @Query("select new com.petprojects.webapi.dto.StudentSimpleDTO(s.id, s.firstName, s.lastName) from Student s " +
            "where s.firstName LIKE %?1% OR s.lastName LIKE %?1%")
    List<StudentSimpleDTO> getStudentByName(String name);

    @Query("select s from Student s " +
            "LEFT JOIN FETCH s.teachers " +
            "where s.id=?1")
    Student getStudentById(Long studentId);

}
