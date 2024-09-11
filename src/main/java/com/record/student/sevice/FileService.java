package com.record.student.sevice;

import com.record.student.model.File;
import com.record.student.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepo fileRepo;

    public File storeFile(MultipartFile file,String name) throws IOException {

        return fileRepo.save(new File(name,file.getContentType() ,file.getBytes()));
    }

    public List<File> getAllFiles() throws IOException {
        return fileRepo.findAll();
    }

    public Optional<File> getFile(int id) throws IOException {
        return this.fileRepo.findById(id);
    }
}
