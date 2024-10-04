package sk.ukf.REST_API.dao;

import sk.ukf.REST_API.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

//    Student findById(int id);
//
//    Student save(Student student);
//
//    void deleteById(int id);
}
