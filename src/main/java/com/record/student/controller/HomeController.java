package com.record.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.record.student.model.Student;
import com.record.student.service.StudentService;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/index")
    public String index(Model m) {

        m.addAttribute("tittle", "Home");
        return "index";
    }


    @GetMapping("/search")
    public String home(Model m) {

        m.addAttribute("tittle", "Search Result");
        return "search_result";
    }

    @GetMapping("/search/student")
    public String searchStudent(@RequestParam("id") String id, @RequestParam("email") String email, Model m) {

        System.out.println("id: " + id);
        System.out.println("name: " + email);

        try {

            Student student = this.studentService.getStudentByIdAndEmail(id, email).get();

            System.out.println(student);

            m.addAttribute("s", student);
            m.addAttribute("tittle", "Result : "+student.getName());
            return "view_result";

        } catch (Exception e) {
            
            m.addAttribute("message", "Student Not Found");
            System.out.println(e.getMessage());
            m.addAttribute("tittle", "Result Not Found");
            return "search_result";

        }

    }

}
