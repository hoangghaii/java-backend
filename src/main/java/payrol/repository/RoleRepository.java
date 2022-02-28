package payrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payrol.models.ERole;
import payrol.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
