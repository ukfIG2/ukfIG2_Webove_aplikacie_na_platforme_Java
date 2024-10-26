package sk.ukf.__MVC_Employee_DU.service;

import sk.ukf.__MVC_Employee_DU.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee student);

    void deleteById(int id);


}
