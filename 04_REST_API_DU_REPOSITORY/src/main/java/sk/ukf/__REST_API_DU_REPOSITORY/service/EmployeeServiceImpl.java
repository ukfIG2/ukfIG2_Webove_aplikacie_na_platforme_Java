package sk.ukf.__REST_API_DU_REPOSITORY.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.__REST_API_DU_REPOSITORY.dao.EmployeeDAO;
import sk.ukf.__REST_API_DU_REPOSITORY.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {

        System.out.println("EmployeeServiceImpl -> findAll(");

        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        System.out.println("EmployeeServiceImpl -> findById(" + id + ")");
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        System.out.println("EmployeeServiceImpl -> save(" + employee.getFirstName() + " " + employee.getLastName() + ")");
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        System.out.println("EmployeeServiceImpl -> deleteById(" + id + ")");
        employeeDAO.deleteById(id);

    }
}
