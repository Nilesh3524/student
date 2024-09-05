package com.record.student.sevice;

import java.util.List;
import java.util.Optional;

import com.record.student.model.Subjects;
import com.record.student.repo.SubjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class SubjectsService {

    @Autowired
    private SubjectsRepo subjectsRepo;

    public Optional<List<Subjects>> getSubjectsByStudentRollNo(String rollNo){
        return this.subjectsRepo.findByStudentRollNo(rollNo);
    }

}
