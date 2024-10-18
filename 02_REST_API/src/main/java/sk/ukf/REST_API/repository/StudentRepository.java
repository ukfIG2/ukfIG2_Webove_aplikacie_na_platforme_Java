package sk.ukf.REST_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.REST_API.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
