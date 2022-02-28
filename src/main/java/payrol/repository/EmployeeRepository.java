package payrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payrol.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
