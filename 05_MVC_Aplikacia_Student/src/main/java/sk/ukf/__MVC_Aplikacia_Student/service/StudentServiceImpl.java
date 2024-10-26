package sk.ukf.__MVC_Aplikacia_Student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.__MVC_Aplikacia_Student.dao.StudentRepository;
import sk.ukf.__MVC_Aplikacia_Student.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Transactional
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}