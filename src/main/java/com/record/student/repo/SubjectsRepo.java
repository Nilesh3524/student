package com.record.student.repo;

import com.record.student.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.util.List;
import java.util.Optional;


public interface SubjectsRepo extends JpaRepository<Subjects,Integer>{

    @Query("from Subjects s where s.student.rollNo = :rollNo")
    Optional<List<Subjects>> findByStudentRollNo(@Param("rollNo") String rollNo);

}
