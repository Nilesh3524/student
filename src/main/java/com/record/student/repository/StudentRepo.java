package com.record.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.student.model.Student;


public interface StudentRepo extends JpaRepository<Student,String>{
    
    Optional<Student> findById(String id);

    Optional<Student> findByIdAndName(String id, String name);

    Optional<Student> findByIdAndEmail(String id, String email);
}
