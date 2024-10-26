package sk.ukf.__MVC_Aplikacia_Student.service;

import sk.ukf.__MVC_Aplikacia_Student.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);

    void deleteById(int id);

}
