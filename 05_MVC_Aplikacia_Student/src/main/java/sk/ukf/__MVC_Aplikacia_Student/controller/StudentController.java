package sk.ukf.__MVC_Aplikacia_Student.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sk.ukf.__MVC_Aplikacia_Student.entity.Student;
import sk.ukf.__MVC_Aplikacia_Student.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    // Potrebujeme injektovať našu Service pre študentov
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Odstráni nadbytočné medzery zo začiatku a konca reťazca
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String listStudents(Model model) {

        // Získať študentov zo služby
        List<Student> students = studentService.findAll();

        // Pridať študentov do modelu
        model.addAttribute("students", students);

        return "students/list";
    }

    @GetMapping("/view")
    public String viewStudent(@RequestParam("studentId") int id, Model model) {

        // Získať študenta zo služby
        Student student = studentService.findById(id);

        // Nastaviť študenta ako model atribút
        model.addAttribute("student", student);

        // Poslať na náš view
        return "students/view";
    }

    @GetMapping("/form/add")
    public String showFormForAdd(Model model) {

        // Vytvoriť model atribút pre viazanie údajov z formulára
        Student student = new Student();

        model.addAttribute("student", student);

        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "students/form";
        }

        studentService.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/form/update")
    public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {

        // Získať študenta zo služby
        Student student = studentService.findById(id);

        // Nastaviť študenta ako model atribút pre predvyplnenie formulára
        model.addAttribute("student", student);

        // Poslať na náš formulár
        return "students/form";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") int id) {
        studentService.deleteById(id);
        return "redirect:/students/list";
    }
}
