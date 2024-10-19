package sk.ukf.__REST_API_DU_REPOSITORY.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf.__REST_API_DU_REPOSITORY.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query =
                entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> employees = query.getResultList();

        System.out.println("EmployeeDAOJpaImpl -> findAll()");

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);

        System.out.println("EmployeeDAOJpaImpl -> findById(" + id + ")");

        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee employee_db = entityManager.merge(employee);

        System.out.println("EmployeeDAOJpaImpl -> save(" + employee.getFirstName() + " " + employee.getLastName() + ")");

        return employee_db;
    }

    @Override
    public void deleteById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            System.out.println("EmployeeDAOJpaImpl -> deleteById(" + id + ")");
            entityManager.remove(employee);
        } else {
            System.out.println("Employee not found with id: " + id);
        }

    }
}
