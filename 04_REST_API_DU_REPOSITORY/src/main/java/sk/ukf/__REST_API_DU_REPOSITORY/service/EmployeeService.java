package sk.ukf.__REST_API_DU_REPOSITORY.service;

import sk.ukf.__REST_API_DU_REPOSITORY.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);

}
