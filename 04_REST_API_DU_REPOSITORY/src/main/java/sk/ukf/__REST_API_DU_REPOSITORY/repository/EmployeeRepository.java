package sk.ukf.__REST_API_DU_REPOSITORY.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.__REST_API_DU_REPOSITORY.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
