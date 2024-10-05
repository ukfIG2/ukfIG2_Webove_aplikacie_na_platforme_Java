package sk.ukf.__REST_API_DU.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
}
