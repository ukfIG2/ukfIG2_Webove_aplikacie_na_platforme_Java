package sk.ukf.__REST_API_DU.DAO;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf.__REST_API_DU.ENTITY.employee;

import java.util.List;

@Repository
public class employeeDAOImpl implements employeeDAO {
    private EntityManager entityManager;

    @Autowired
    public employeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<employee> getAllEmployees() {
        //return entityManager.createQuery("SELECT e FROM employee e", employee.class).getResultList();
        TypedQuery<employee> query =
                entityManager.createQuery("FROM employee", employee.class);
        List<employee> employees = query.getResultList();
        System.out.println("EmployeesDaoImpl - Employees list returned!");
        return employees;
    }

    @Override
    public employee findById(int id) {
        System.out.println("EmployeesDaoImpl - Employee findById " + id);
        return entityManager.find(employee.class, id);
    }

    @Override
    public employee save(employee Employee) {
        System.out.println("EmployeesDaoImpl - Employee saved " + Employee);
        return entityManager.merge(Employee);
    }

    @Override
    public void deleteById(int id) {
        employee Employee = entityManager.find(employee.class, id);
        if (Employee != null){
            entityManager.remove(Employee);
        }
        else {
            System.out.println("EmployeesDaoImpl - Employee not found for deletion with id " + id);
        }
    }
}
