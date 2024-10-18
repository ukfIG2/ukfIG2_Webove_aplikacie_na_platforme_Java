package sk.ukf.REST_API.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import sk.ukf.REST_API.entity.Student;
import sk.ukf.REST_API.service.StudentService;
import sk.ukf.REST_API.entity.ResponseStatus;

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
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable int id) {
        Student findStudent = studentService.findById(id);
        if (findStudent == null) {
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Student id (" + id + ") not found",
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findStudent, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> addStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                sb.append(error.getDefaultMessage()).append("\n");
            }
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.BAD_REQUEST.value(),
                    sb.toString(),
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        student.setId(0);
        studentService.save(student);
        ResponseStatus successResponse = new ResponseStatus(
                HttpStatus.OK.value(),
                "Student added successfully",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student existingStudent = studentService.findById(id);
        if (existingStudent == null) {
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Student id (" + id + ") not found",
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        student.setId(id);
        Student updatedStudent = studentService.save(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Student id (" + id + ") not found",
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id);
        ResponseStatus successResponse = new ResponseStatus(
                HttpStatus.OK.value(),
                "Deleted student id - " + id,
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
