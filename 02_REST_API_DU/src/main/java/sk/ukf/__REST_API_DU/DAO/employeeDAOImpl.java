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
        return employees;
    }
}
