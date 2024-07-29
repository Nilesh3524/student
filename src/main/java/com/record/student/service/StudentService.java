package com.record.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.record.student.model.Student;
import com.record.student.repository.StudentRepo;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepo studentRepo;

    public Optional<Student> save(Student student){
        return Optional.of(this.studentRepo.save(student));
    }


    public boolean isStudentExists(Student student){

        boolean f = false;

        if (this.studentRepo.findById(student.getId()).isPresent()) {
            f = true;
        }

        return f;

    }

    public Optional<List<Student>> getAllStudents(){
        return Optional.ofNullable(this.studentRepo.findAll());
    }

    public Optional<Student> getStudentByIdAndName(String id, String name){
        return this.studentRepo.findByIdAndName(id, name);
    }


    public void deleteStudentById(String id){

        this.studentRepo.deleteById(id);
    }

    public Optional<Student> getStudentById(String id){

        return this.studentRepo.findById(id);
    }
    
    
    public Optional<Student> getStudentByIdAndEmail(String id,String email){

        return this.studentRepo.findByIdAndEmail(id,email);
    }

}
