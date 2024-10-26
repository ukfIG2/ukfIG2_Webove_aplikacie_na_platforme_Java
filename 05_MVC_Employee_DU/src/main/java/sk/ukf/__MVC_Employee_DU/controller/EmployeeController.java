package sk.ukf.__MVC_Employee_DU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.ukf.__MVC_Employee_DU.entity.Employee;
import sk.ukf.__MVC_Employee_DU.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService empleyeeService;;

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

}
