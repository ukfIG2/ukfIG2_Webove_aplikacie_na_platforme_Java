package sk.ukf.__REST_API_DU.SERVICE;

import sk.ukf.__REST_API_DU.ENTITY.employee;

import java.util.List;

public interface employeeService {

    List<employee> findAll();

    employee findById(int id);

    employee save(employee Employee);

    void deleteById(int id);
}
