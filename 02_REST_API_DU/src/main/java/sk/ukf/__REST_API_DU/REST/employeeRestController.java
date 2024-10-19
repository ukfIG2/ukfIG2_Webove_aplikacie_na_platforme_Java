package sk.ukf.__REST_API_DU.REST;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import sk.ukf.__REST_API_DU.ENTITY.employee;
import sk.ukf.__REST_API_DU.SERVICE.employeeService;
import sk.ukf.__REST_API_DU.ENTITY.ResponseStatus;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class employeeRestController {
    private employeeService employeeService;

    @Autowired
    public employeeRestController(employeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*@GetMapping("/employees")
    public List<employee> getAllEmployees() {
        System.out.print("employesRestController - getAllEmployees");
        return employeeService.findAll();
    }*/

    @GetMapping("/employees")
    public ResponseEntity<List<employee>> findAll(){
        System.out.println("employesRestController - getAllEmployees");
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    /*@GetMapping("/employees/{id}")
    public employee findById(@PathVariable int id) {
        System.out.println("employesRestController - getEmployeeById");
        employee Employee = employeeService.findById(id);
        if (Employee == null) {
            throw new RuntimeException("Employee not found for id " + id);
        }
        return Employee;
    }*/

    @GetMapping("/employees/{id}")
public ResponseEntity<Object> getEmployees(@PathVariable int id) {
    System.out.println("employesRestController - getEmployeeById");
    employee findEmployee = employeeService.findById(id);
    System.out.println("employeesRestController - getEmploy " + findEmployee);
    if (findEmployee == null) {
        //return new ResponseEntity<>("Employee not found for id " + id, HttpStatus.NOT_FOUND);
        ResponseStatus errorResponse = new ResponseStatus(
                HttpStatus.NOT_FOUND.value(),
                "Employee id (" + id + ") not found",
                System.currentTimeMillis()
        );
        System.out.println("Employee id timestamps is " + new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(findEmployee, HttpStatus.OK);
}

    /*@PostMapping("employees")
    public employee addEmployee(@RequestBody employee employee) {
        System.out.println("employesRestController - addEmployee");
        return employeeService.save(employee);
    }*/

    @PostMapping("employees")
    public ResponseEntity<Object> addEmployees(@Valid @RequestBody employee Employee, BindingResult result){
        System.out.println("employesRestController - addEmployee");
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
        //Employee.setId(0);
        employeeService.save(Employee);
        ResponseStatus successResponse = new ResponseStatus(
                HttpStatus.OK.value(),
                "Employee saved successfully.",
                System.currentTimeMillis()
        );
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    /*@PutMapping("/employees/{id}")
    public employee updateEmployee(@PathVariable int id, @RequestBody employee employee) {
        System.out.println("employesRestController - updateEmployee");
        employee.setId(id);
        employee employeeExists = employeeService.findById(id);
        if (employeeExists == null) {
            throw new RuntimeException("Employee not found for id " + id);
        }
        return employeeService.save(employee);
    }*/

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable int id, @Valid @RequestBody employee employee, BindingResult result) {
        System.out.println("employesRestController - updateEmployee");

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

        employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Employee id (" + id + ") not found",
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        //employee.setId(id);
        employee updatedEmployee = employeeService.save(employee);

        ResponseStatus successResponse = new ResponseStatus(
                HttpStatus.OK.value(),
                "Employee updated successfully with id " + id,
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }




    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable int id) {
        System.out.println("employesRestController - deleteEmployee");

        employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Employee id (" + id + ") not found",
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        employeeService.deleteById(id);

        ResponseStatus successResponse = new ResponseStatus(
                HttpStatus.OK.value(),
                "Employee with id " + id + " deleted successfully",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }



}
