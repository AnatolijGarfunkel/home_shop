package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Order;
import project.enums.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(
            nativeQuery = true,
            value = "select * from orders where user_id = :userId and status = :status"
    )
    Order findByUserIdAndStatusContains(Long userId, OrderStatus status);

    List<Order> findAllByUserId(Long userId);
}
