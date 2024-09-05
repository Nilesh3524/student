package com.record.student.repo;


import com.record.student.model.SGPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface SGPARepo extends JpaRepository<SGPA,Integer> {

    @Query("from SGPA s where s.student.rollNo = :rollNo")
    Optional<SGPA> findByStudentRollNo(@Param("rollNo") String rollNo);

}
