package sk.ukf.__MVC_Aplikacia_Student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.__MVC_Aplikacia_Student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

