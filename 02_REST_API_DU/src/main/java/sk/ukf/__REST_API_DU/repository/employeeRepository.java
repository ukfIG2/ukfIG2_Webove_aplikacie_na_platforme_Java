package sk.ukf.__REST_API_DU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.__REST_API_DU.ENTITY.employee;

public interface employeeRepository extends JpaRepository<employee, Integer> {
}
