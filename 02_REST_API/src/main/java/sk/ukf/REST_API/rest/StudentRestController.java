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

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable int id) {
     Student student = studentService.findById(id);

     if (student == null) {
         throw new RuntimeException("Student not found for id: " + id);
     }
     return student;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        student.setId(0);
        Student student_db = studentService.save(student);
        return student_db;
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student existingStudent = studentService.findById(id);
        if (existingStudent == null) {
            throw new RuntimeException("Student id not found - " + id);
        }
        student.setId(id);
        Student updatedStudent = studentService.save(student);
        return updatedStudent;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        Student student = studentService.findById(id);

        if (student == null) {
            throw new RuntimeException("Student id not found - " + id);
        }

        studentService.deleteById(id);

        return "Deleted student id - " + id;
    }
}

