package sk.ukf.REST_API.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf.REST_API.entity.Student;
import sk.ukf.REST_API.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.findAll();
    }
}

