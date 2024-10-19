package sk.ukf.__REST_API_DU_REPOSITORY.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import sk.ukf.__REST_API_DU_REPOSITORY.entity.Employee;
import sk.ukf.__REST_API_DU_REPOSITORY.service.EmployeeService;
import sk.ukf.__REST_API_DU_REPOSITORY.entity.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final View error;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, View error) {
        this.employeeService = employeeService;
        this.error = error;
    }

    // GET: Get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll() {
        System.out.println("EmployeeRestController -> findAll()");
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    // GET: Find employee by id
    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        System.out.println("EmployeeRestController -> findById(" + id + ")");

        try {
            Employee findEmployee = employeeService.findById(id);
            System.out.println("EmployeeRestController -> findEmployee " + findEmployee.getFirstName() + " " + findEmployee.getLastName());
            return new ResponseEntity<>(findEmployee, HttpStatus.OK);
        } catch (RuntimeException e) {
            System.out.println("EmployeeRestController -> findById(" + id + ") Not found");
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Employee id (" + id + ") not found",
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An unexpected error occurred: " + e.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // POST: Save a new employee
    @PostMapping("/employee")
    public ResponseEntity<Object> save(@Valid @RequestBody Employee employee, BindingResult result) {
        System.out.println("EmployeeRestController -> save(" + employee.getFirstName() + " " + employee.getLastName() + ")");
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                sb.append(error.getDefaultMessage()).append("\n");
            }
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.BAD_REQUEST.value(),
                    sb.toString(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        employeeService.save(employee);
        ResponseStatus successResponse = new ResponseStatus(
                HttpStatus.OK.value(),
                "Employee saved successfully",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    // PUT: Update an existing employee
    @PutMapping("/employee/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee, BindingResult result) {
        System.out.println("EmployeeRestController -> updateEmployee(" + id + ")");

        try {
            // Validate incoming data
            if (result.hasErrors()) {
                StringBuilder sb = new StringBuilder();
                for (ObjectError error : result.getAllErrors()) {
                    sb.append(error.getDefaultMessage()).append("\n");
                }
                ResponseStatus errorResponse = new ResponseStatus(
                        HttpStatus.BAD_REQUEST.value(),
                        sb.toString(),
                        LocalDateTime.now()
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            // Check if employee exists
            Employee existingEmployee = employeeService.findById(id);
            if (existingEmployee == null) {
                ResponseStatus errorResponse = new ResponseStatus(
                        HttpStatus.NOT_FOUND.value(),
                        "Employee id (" + id + ") not found",
                        LocalDateTime.now()
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            employee.setId(id);
            Employee updatedEmployee = employeeService.save(employee);

            ResponseStatus successResponse = new ResponseStatus(
                    HttpStatus.OK.value(),
                    "Employee updated successfully with id " + updatedEmployee.getId(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);

        } catch (RuntimeException e) {
            System.out.println("EmployeeRestController -> updateEmployee: " + e.getMessage());
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Employee id (" + id + ") not found",
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An unexpected error occurred: " + e.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // DELETE: Delete an employee by id
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable int id) {
        System.out.println("EmployeeRestController -> deleteEmployee(" + id + ")");

        try {
            Employee existingEmployee = employeeService.findById(id);
            if (existingEmployee == null) {
                ResponseStatus errorResponse = new ResponseStatus(
                        HttpStatus.NOT_FOUND.value(),
                        "Employee id (" + id + ") not found",
                        LocalDateTime.now()
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            // Perform deletion
            employeeService.deleteById(id);

            ResponseStatus successResponse = new ResponseStatus(
                    HttpStatus.OK.value(),
                    "Employee deleted successfully",
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);

        } catch (RuntimeException e) {
            System.out.println("EmployeeRestController -> deleteEmployee: " + e.getMessage());
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.NOT_FOUND.value(),
                    "Employee id (" + id + ") not found",
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            ResponseStatus errorResponse = new ResponseStatus(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An unexpected error occurred: " + e.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

