package payrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payrol.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
