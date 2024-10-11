package sk.ukf.REST_API.service;

import sk.ukf.REST_API.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);

    void deleteById(int id);

}
