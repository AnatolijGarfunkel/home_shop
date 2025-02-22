package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
