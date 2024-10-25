package sk.ukf.__MVC_CVICENIE_2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sk.ukf.__MVC_CVICENIE_2.entity.Student;

import java.util.List;

@Controller
public class StudentController {

    // Načítanie zoznamu krajín z konfigurácie
    @Value("${countries}")
    private List<String> countries;

    // Zobrazí formulár pre študenta
    @GetMapping("/zobrazFormular")
    public String zobrazFormular(Model model) {

        // Vytvorenie objektu študenta
        Student student = new Student();

        // Pridanie objektu študenta do modelu
        model.addAttribute("student", student);

        // Pridanie zoznamu krajín do modelu
        model.addAttribute("countries", countries);

        return "student-form";
    }

    // Spracuje údaje z formulára pre študenta
    @PostMapping("/spracujFormular")
    public String spracujFormular(@ModelAttribute("student") Student student) {
        return "student-confirmation";
    }
}
