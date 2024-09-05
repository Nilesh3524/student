package com.record.student.controller;

import com.record.student.sevice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "student/dashboard";
    }

    @GetMapping("/login")
    public String studentLogin(Model m) {

        m.addAttribute("title", "Student login");

        return "/student/login";
    }



}