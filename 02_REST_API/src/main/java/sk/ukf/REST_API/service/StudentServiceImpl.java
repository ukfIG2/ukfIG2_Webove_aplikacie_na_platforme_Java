package sk.ukf.REST_API.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.REST_API.dao.StudentDAO;
import sk.ukf.REST_API.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentDAO.findById(id);
    }

    @Transactional
    @Override
    public Student save(Student student) {
       return studentDAO.save(student);    }

    @Transactional
    @Override
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }
}

