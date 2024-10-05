package sk.ukf.__REST_API_DU.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.__REST_API_DU.ENTITY.employee;
import sk.ukf.__REST_API_DU.SERVICE.employeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class employeeRestController {
    private employeeService employeeService;

    @Autowired
    public employeeRestController(employeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
