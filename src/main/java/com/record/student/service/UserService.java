package com.record.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.record.student.model.User;

@Service
public class UserService {
    

    private List<User> users = new ArrayList<>();

    public UserService(){

        users.add(new User("himanshu"));

    }

}
