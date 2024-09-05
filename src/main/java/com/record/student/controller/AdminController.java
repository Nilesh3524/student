package com.record.student.controller;

import com.record.student.model.Attendence;
import com.record.student.model.SGPA;
import com.record.student.model.Student;
import com.record.student.sevice.SGPAService;
import com.record.student.sevice.StudentService;
import com.record.student.sevice.SubjectsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SGPAService sgpaService;

    @Autowired
    private SubjectsService subjectsService;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //all students
    @ModelAttribute
    public void allStudents(Model m){

        List<Student> allStudents = this.studentService.getAllStudents();

        m.addAttribute("students",allStudents);

    }


    // home page
    @GetMapping("/dashboard")
    public String dashboard(Model m) {

        m.addAttribute("title", "DASHBOARD");

        return "admin/home";
    }

    // admin login page
    @GetMapping("/login")
    public String login() {

        return "admin/login";
    }

    // add student page
    @GetMapping("/add-student")
    public String addStudentForm(Model m) {

        m.addAttribute("title", "Add Student");

        return "admin/addStudent";
    }

    // process add student
    @PostMapping("/process-add-student")
    public String addStudent(@ModelAttribute("student") Student student) {

        System.out.println(student);

        if (this.studentService.isStudentExits(student.getRollNo())
                || this.studentService.isStudentExitsByEmail(student.getEmail())) {
            return "admin/addStudent";
        }

        student.setPassword(passwordEncoder().encode(student.getEmail()));

        student.setRole("STUDENT");

        this.studentService.addStudent(student);

        return "redirect:dashboard";

    }

    // search for sgpa page
    @GetMapping("/search-sgpa")
    public String searchForSGPA() {

        return "admin/search";
    }

    // add sgpa page
    @GetMapping("/add-sgpa")
    public String addSGPAForm(@RequestParam("rollNo") String rollNo, Model m) {

        System.out.println(rollNo);

        m.addAttribute("rollNo", rollNo);

        if (this.studentService.isStudentExits(rollNo)) {

            return "admin/addSGPA";

        } else {
            return "admin/search";
        }

    }

    // process add sgpa
    @PostMapping("/process-add-sgpa")
    public String addSgpa(@ModelAttribute("sgpa") SGPA sgpa, @RequestParam("rollNo") String rollNo) {

        System.out.println(sgpa);

        System.out.println(rollNo);

        if (this.studentService.isStudentExits(rollNo)) {

            Student student = this.studentService.getStudentByRollNo(rollNo).get();

            sgpa.setStudent(student);

            student.getSgpa().add(sgpa);

            double avgSgpa = (sgpa.getSgpa1() + sgpa.getSgpa2() + sgpa.getSgpa3() + sgpa.getSgpa4() + sgpa.getSgpa5()
                    + sgpa.getSgpa6() + sgpa.getSgpa7() + sgpa.getSgpa8()) / 8;

            student.setAvgSGPA(avgSgpa);

            // SGPA savedSGPA = this.sgpaService.addSGPA(sgpa);

            this.studentService.addStudent(student);

            // logger.info(savedSGPA.toString());

            // logger.info(updatedStudent.toString());

            return "redirect:dashboard";

        } else {
            return "admin/search";
        }

    }

    // search attendence page
    @GetMapping("/search-attendence")
    public String searchForAttendence() {

        return "admin/search_attendence";
    }

    // add attendence page
    @GetMapping("/add-attendence")
    public String addAttendenceForm(@RequestParam("rollNo") String rollNo, Model m) {

        System.out.println(rollNo);

        if (this.studentService.isStudentExits(rollNo)) {

            m.addAttribute("rollNo", rollNo);
            return "admin/addAttendence";

        } else {

            return "admin/search_attendence";
        }

    }

    // process add attendence
    @PostMapping("/process-add-attendence")
    public String processAddAttendence(@ModelAttribute("attendence") Attendence attendence,
                                       @RequestParam("rollNo") String rollNo) {

        // logger.info(rollNo);
        // logger.info(attendence.toString());

        if (this.studentService.isStudentExits(rollNo)) {

            Student student = this.studentService.getStudentByRollNo(rollNo).get();

            attendence.setStudent(student);

            student.getAttendence().add(attendence);

            double avgAttendence = (attendence.getA1() + attendence.getA2() + attendence.getA3() + attendence.getA4()
                    + attendence.getA5() + attendence.getA6() + attendence.getA7() + attendence.getA8()) / 8;

            student.setAvgAttendence(avgAttendence);

            this.studentService.addStudent(student);

            return "redirect:dashboard";

        } else {
            return "admin/addAttendence";
        }

    }

    // search for subject form
    @GetMapping("/search-subject")
    public String searchSubjectForm() {

        return "admin/search_subjects";
    }

    // add subject page
    @GetMapping("/add-subject")
    public String addSubjectForm(@RequestParam("rollNo") String rollNo, Model m) {

        System.out.println(rollNo);

        if (this.studentService.isStudentExits(rollNo)) {

            m.addAttribute("rollNo", rollNo);

            return "admin/addSubject";

        } else {

            return "admin/search_subjects";
        }

    }

    // cources page
    @GetMapping("/cources")
    public String cources() {
        return "admin/cources";
    }

    //all students page
    @GetMapping("/all-students")
    public String allStudents(){
        return "admin/allStudents";
    }


    //Backlog students page
    @GetMapping("/backlog-students")
    public String backlogStudents(){
        return "admin/backlogStudents";
    }


    //delete student
    @GetMapping("/student/delete/{rollNo}")
    public String deleteStudent(@PathVariable String rollNo) {

//        System.out.println(rollNo);

        if (this.studentService.isStudentExits(rollNo)) {

            this.studentService.deleteStudent(rollNo);

            return "redirect:/admin/all-students";

        }else {

            return "redirect:dashboard";

        }

    }

//    update student
    @GetMapping("/student/update/{rollNo}")
    public String updateStudent(@PathVariable String rollNo, Model m){

        return "admin/updateStudent";
    }
}
