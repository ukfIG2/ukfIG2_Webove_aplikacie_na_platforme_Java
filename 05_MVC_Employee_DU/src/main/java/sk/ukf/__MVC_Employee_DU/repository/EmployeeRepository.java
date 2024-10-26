package sk.ukf.__MVC_Employee_DU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.__MVC_Employee_DU.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
