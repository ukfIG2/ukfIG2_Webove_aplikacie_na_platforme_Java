package sk.ukf.REST_API.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.logging.Log;
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

    @Override
    public Student findById(int id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    @Override
    public Student save(Student student) {
        Student student_db = entityManager.merge(student);
        return student_db;
    }

    @Override
    public void deleteById(int id) {
        Student student = entityManager.find(Student.class, id);
        if (student!= null) {
            entityManager.remove(student);
        }
        else {
            //Log error in class studentdaoimpl deleteby id
            System.err.println("Student not found with id: " + id);
        }
    }
}