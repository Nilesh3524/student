package com.record.student.sevice;

import java.util.Optional;

import com.record.student.model.SGPA;
import com.record.student.repo.SGPARepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class SGPAService {

    @Autowired
    private SGPARepo sgpaRepo;

    public Optional<SGPA> getSGPAByStudentRollNo(String rollNo){
        return this.sgpaRepo.findByStudentRollNo(rollNo);
    }

    public SGPA addSGPA(SGPA sgpa){
        return this.sgpaRepo.save(sgpa);
    }

}

