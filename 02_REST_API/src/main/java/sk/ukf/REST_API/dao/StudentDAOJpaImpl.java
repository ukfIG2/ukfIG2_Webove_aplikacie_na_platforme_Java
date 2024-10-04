package sk.ukf.REST_API.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf.REST_API.entity.Student;

import java.util.List;

@Repository
public class StudentDAOJpaImpl implements StudentDAO {
    private EntityManager entityManager;

    @Autowired
    public StudentDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        return students;
    }
}