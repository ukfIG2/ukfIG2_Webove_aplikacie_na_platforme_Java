package sk.ukf.__REST_API_DU.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.__REST_API_DU.DAO.employeeDAO;
import sk.ukf.__REST_API_DU.ENTITY.employee;
import sk.ukf.__REST_API_DU.repository.employeeRepository;

import java.util.List;

@Service
public class employeeServiceImpl implements employeeService {
    //private employeeDAO employeeDAO;
    private employeeRepository EmployeeRepository;

    @Autowired
    /*public employeeServiceImpl(employeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }*/
    public employeeServiceImpl(employeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    @Override
    public List<employee> findAll() {
        System.out.println("employeeServiceImpl - getAllEmployees");
        //return employeeDAO.getAllEmployees();
        return EmployeeRepository.findAll();
    }

    @Override
    public employee findById(int id) {
        return EmployeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Transactional
    @Override
    public employee save(employee Employee) {
        System.out.println("employeeServiceImpl - save " + Employee);
        //return employeeDAO.save(Employee);
        return EmployeeRepository.save(Employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if (!EmployeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        EmployeeRepository.deleteById(id);
    }
}
