package sk.ukf.__REST_API_DU.DAO;

import sk.ukf.__REST_API_DU.ENTITY.employee;

import java.util.List;

public interface employeeDAO {
    //findall
    List<employee> getAllEmployees();

    employee findById(int id);

    employee save(employee Employee);

    void deleteById(int id);
}
