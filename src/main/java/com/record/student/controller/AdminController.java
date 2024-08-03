package com.record.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.record.student.helper.EmailMessage;
import com.record.student.helper.Message;
import com.record.student.model.Student;
import com.record.student.service.EmailService;
import com.record.student.service.StudentService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/home")
    public String home(Model m, HttpSession session) {

        List<Student> students = this.studentService.getAllStudents().get();

        m.addAttribute("students", students);

        // session.setAttribute("message", new Message());

        m.addAttribute("tittle", "ADMIN HOME");

        return "home";
    }

    @GetMapping("/home/students-with-backlog")
    public String backlogStudents(Model m, HttpSession session) {

        List<Student> studentsWithBacklog = this.studentService.getAllStudents()
                .get()
                .stream()
                .filter(s -> s.getBacklog().equals("yes"))
                .toList();

        // System.out.println(studentsWithBacklog);

        m.addAttribute("students", studentsWithBacklog);

        // session.setAttribute("message", new Message());

        m.addAttribute("tittle", "ADMIN HOME");

        return "backlog_student";
    }

    @GetMapping("/add-student")
    public String addStudent(Model m, HttpSession session) {

        m.addAttribute("tittle", "Add Student Record");
        // session.setAttribute("message", new Message());
        m.addAttribute("student", new Student());

        return "addStudent";
    }

    @PostMapping("/process-add-student")
    public String processAddStudent(@Valid @ModelAttribute("student") Student student, BindingResult res, Model m,
            HttpSession session) {

        if (res.hasErrors()) {
            m.addAttribute("student", student);
            return "addStudent";
        }

        if (this.studentService.isStudentExists(student)) {

            // att.addAttribute("message", new Message("Student record already exits",
            // "alert-danger"));

            session.setAttribute("message", new Message("Student Record Already Exits", "alert-danger"));

            return "addStudent";

        }

        double totalSgpa = student.getSgpa1() + student.getSgpa2() + student.getSgpa3() + student.getSgpa4()
                + student.getSgpa5() + student.getSgpa6() + student.getSgpa7() + student.getSgpa8();

        double cgpa = totalSgpa / 8;

        student.setCgpa(cgpa);

        double totalAttendance = student.getAttendance1() + student.getAttendance2() + student.getAttendance3()
                + student.getAttendance4() + student.getAttendance5() + student.getAttendance6()
                + student.getAttendance7() + student.getAttendance8();

        double avgAttendance = totalAttendance / 8;

        student.setAvgAttendance(avgAttendance);

        System.out.println(student);

        try {

            System.out.println("saving student");

            this.studentService.save(student);
            session.setAttribute("message", new Message("Student Record Added", "alert-success"));
            // att.addAttribute("message", new Message("Student Record Added",
            // "alert-success"));

            return "redirect:home";

        } catch (Exception e) {

            System.out.println(e.getMessage());

            System.out.println("Exception");

            m.addAttribute("message", new Message("Something went wrong !!", "alert-danger"));

            return "redirect:add-student";

        }

    }

    @GetMapping("/login")
    public String login(Model m) {

        m.addAttribute("tittle", "Admin Login");
        return "login";
    }

    @PostMapping("/process-login")
    public String processLogin(HttpSession session, BindingResult res) {

        // Message message = Message.builder().content("Login
        // Success").type("alert-success").build();

        session.setAttribute("message", new Message("message", "alert-success"));

        return "redirect:/admin/home";
    }

    @GetMapping("/student/{id}")
    public String showDetails(@PathVariable("id") String id, Model m) {

        try {

            Student student = this.studentService.getStudentById(id).get();

            Optional.ofNullable(student)
                    .ifPresentOrElse(s -> {

                        m.addAttribute("s", student);

                    }, () -> {
                    });

            m.addAttribute("tittle", student.getName());

            return "show_details";

        } catch (Exception e) {

            System.out.println("student not found !!");

            // m.addAttribute("s", new Student());

            return "redirect:/admin/home";
        }

    }

    @GetMapping("/student/update/{id}")
    public String updateStudent(@PathVariable("id") String id, Model m) {

        try {

            Student student = this.studentService.getStudentById(id).get();

            m.addAttribute("student", student);

            m.addAttribute("tittle", "update student : " + student.getId());

            return "update_student";

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("student not found !!");
            return "redirect:/admin/home";
        }

    }

    @PostMapping("/update/student/{id}")
    public String updateFormHandler(@Valid @ModelAttribute("student") Student student, BindingResult res,
            @PathVariable("id") String id, Model m, HttpSession session) {

        if (res.hasErrors()) {

            m.addAttribute("student", student);

            return "update_student";
        }

        student.setId(id);

        double totalSgpa = student.getSgpa1() + student.getSgpa2() + student.getSgpa3() + student.getSgpa4()
                + student.getSgpa5() + student.getSgpa6() + student.getSgpa7() + student.getSgpa8();

        double cgpa = totalSgpa / 8;

        student.setCgpa(cgpa);

        double totalAttendance = student.getAttendance1() + student.getAttendance2() + student.getAttendance3()
                + student.getAttendance4() + student.getAttendance5() + student.getAttendance6()
                + student.getAttendance7() + student.getAttendance8();

        double avgAttendance = totalAttendance / 8;

        student.setAvgAttendance(avgAttendance);

        this.studentService.save(student);

        // String to = student.getEmail();

        // String message = "Hello, "+student.getName()+"!\n\n"
        // + "Your data has been updated.\n"
        // + student.toString()+"\n\n"
        // + "We hope you're having a great day!\n\n"
        // + "Best regards,\n"
        // + "The S.B Jain Institute";


        this.emailService.sendEmail(student.getEmail(), "Your data has been Updated", EmailMessage.getMessage(student));

        session.setAttribute("message", new Message("Record Updated Successfully", "alert-success"));

        return "redirect:/admin/home";

    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id, HttpSession session) {

        this.studentService.deleteStudentById(id);

        session.setAttribute("message", new Message("Record Deleted Successfully", "alert-success"));

        return "redirect:/admin/home";
    }
}
