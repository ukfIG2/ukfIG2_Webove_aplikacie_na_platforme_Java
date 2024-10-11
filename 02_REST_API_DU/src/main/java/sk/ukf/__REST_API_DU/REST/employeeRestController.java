package sk.ukf.__REST_API_DU.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        System.out.print("employesRestController - getAllEmployees");
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public employee findById(@PathVariable int id) {
        System.out.println("employesRestController - getEmployeeById");
        employee Employee = employeeService.findById(id);
        if (Employee == null) {
            throw new RuntimeException("Employee not found for id " + id);
        }
        return Employee;
    }

    @PostMapping("employees")
    public employee addEmployee(@RequestBody employee employee) {
        System.out.println("employesRestController - addEmployee");
        return employeeService.save(employee);
    }

    @PutMapping("/employees/{id}")
    public employee updateEmployee(@PathVariable int id, @RequestBody employee employee) {
        System.out.println("employesRestController - updateEmployee");
        employee.setId(id);
        employee employeeExists = employeeService.findById(id);
        if (employeeExists == null) {
            throw new RuntimeException("Employee not found for id " + id);
        }
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        System.out.println("employesRestController - deleteEmployee");
        employeeService.deleteById(id);
        if (employeeService.findById(id)!= null) {
            throw new RuntimeException("Employee not found for id " + id);
        }
    }


}
