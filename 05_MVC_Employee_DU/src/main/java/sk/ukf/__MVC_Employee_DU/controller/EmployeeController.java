package sk.ukf.__MVC_Employee_DU.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.ukf.__MVC_Employee_DU.entity.Employee;
import sk.ukf.__MVC_Employee_DU.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService empleyeeService;

    @Value("${jobTitles}")
    private List<String> jobTitleList;

    private List<String> radioButtonsList = List.of(
            "Plny uvazok",
            "Ciastocny uvazok",
            "Dohoda",
            "Stazista/Praktikant"
    );

    @Autowired
    public EmployeeController(EmployeeService empleyeeService) {
        this.empleyeeService = empleyeeService;
    }

    @GetMapping("list")
    public String listEmployees(Model model) {

        //Ziskat employees zo sluzby
        List<Employee> employees = empleyeeService.findAll();

        //Pridat employees do modelu
        model.addAttribute("employees", employees);

        //Nacitat view s employees
        return "employees/list";
    }

    @GetMapping("/view")
    public String viewEmployee(@RequestParam("id") int id, Model model) {

        Employee employee = empleyeeService.findById(id);

        model.addAttribute("employee", employee );

        return "employees/view";
    }

    @GetMapping("/form/add")
    public String showFormForAdd(Model model) {

        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        model.addAttribute("jobTitleList", jobTitleList);

        model.addAttribute("radioButtonsList", radioButtonsList);

        return "employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // If there are validation errors, reload job titles and radio button options
            model.addAttribute("jobTitleList", jobTitleList);
            model.addAttribute("radioButtonsList", radioButtonsList);

            // Return the form view again
            return "employees/form";
        }

        // Save the employee if there are no validation errors
        empleyeeService.save(employee);

        // Redirect to the list view after successful save
        return "redirect:/employees/list";
    }


    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id) {

        empleyeeService.deleteById(id);

        return "redirect:/employees/list";
    }

    @GetMapping("/form/edit")
    public String showFormForEdit(@RequestParam("id") int id, Model model) {
        Employee employee = empleyeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("jobTitleList", jobTitleList);
        model.addAttribute("radioButtonsList", radioButtonsList);
        return "employees/form";
    }

}
