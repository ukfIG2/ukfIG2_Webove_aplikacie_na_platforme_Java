package sk.ukf.__REST_API_DU.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.__REST_API_DU.DAO.employeeDAO;
import sk.ukf.__REST_API_DU.ENTITY.employee;

import java.util.List;

@Service
public class employeeServiceImpl implements employeeService {
    private employeeDAO employeeDAO;

    @Autowired
    public employeeServiceImpl(employeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<employee> findAll() {
        System.out.println("employeeServiceImpl - getAllEmployees");
        return employeeDAO.getAllEmployees();
    }

    @Override
    public employee findById(int id) {
        System.out.println("employeeServiceImpl - findById " + id);
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public employee save(employee Employee) {
        System.out.println("employeeServiceImpl - save " + Employee);
        return employeeDAO.save(Employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        System.out.println("employeeServiceImpl - deletebyid " + id);
        employeeDAO.deleteById(id);

    }
}
