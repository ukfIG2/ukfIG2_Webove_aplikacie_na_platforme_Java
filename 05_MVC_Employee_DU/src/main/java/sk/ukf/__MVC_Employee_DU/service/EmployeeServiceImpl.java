package sk.ukf.__MVC_Employee_DU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.__MVC_Employee_DU.entity.Employee;
import sk.ukf.__MVC_Employee_DU.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        System.out.println("EmployeeServiceImpl -> findAll(");

        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        System.out.println("EmployeeServiceImpl -> findById(" + id + ")");
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        System.out.println("EmployeeServiceImpl -> save(" + employee.getFirstName() + " " + employee.getLastName() + ")");
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        System.out.println("EmployeeServiceImpl -> deleteById(" + id + ")");
        employeeRepository.deleteById(id);


    }
}