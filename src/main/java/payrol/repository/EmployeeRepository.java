package payrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payrol.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
