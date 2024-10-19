package sk.ukf.__REST_API_DU_REPOSITORY.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf.__REST_API_DU_REPOSITORY.entity.Employee;
import sk.ukf.__REST_API_DU_REPOSITORY.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> FindAll(){

        System.out.println("EmployeeRestController -> FindAll(");

        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee FindById(@PathVariable int id){

        Employee employee = employeeService.findById(id);

        if(employee == null){
            System.out.println("EmployeeRestController -> FindById(" + id + ") Not found");
            throw new RuntimeException("Employee not found for id " + id);
        }

        return employee;
    }

    @PostMapping("/employee")
    public Employee employee(@RequestBody Employee employee) {
        //employee.setId(0);
        System.out.println("EmployeeRestController -> employee(" + employee.getFirstName() + " " + employee.getLastName() + ")");
        return employeeService.save(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.findById(id);

        if (existingEmployee == null){
            System.out.println("EmployeeRestController -> updateEmployee(" + id + ") Not found");
            throw new RuntimeException("Employee not found for id " + id);
        }
        //employee.setId(0);
        System.out.println("EmployeeRestController -> updateEmployee(" + id + ")");
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable int id) {
        Employee existingEmployee = employeeService.findById(id);

        if (existingEmployee == null){
            System.out.println("EmployeeRestController -> deleteEmployee(" + id + ") Not found");
            throw new RuntimeException("Employee not found for id " + id);
        }
        employeeService.deleteById(id);
        System.out.println("EmployeeRestController -> deleteEmployee(" + id + ")");
    }

}
