package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(
            nativeQuery = true,
            value = "select * from orders where user_id = :userId and status = 'PENDING'"
    )
    Optional<Order> findByUserIdAndStatusPending(Long userId);

    List<Order> findAllByUserId(Long userId);

    @Query(
            nativeQuery = true,
            value = "select * from orders where status = 'PROCESSING'"
    )
    List<Order> findAllByStatusProcessing();

    @Query(
            nativeQuery = true,
            value = "select * from orders where status = 'SENT'"
    )
    List<Order> findAllByStatusSent();

    @Query(
            nativeQuery = true,
            value = "select * from orders where status = 'PAID'"
    )
    List<Order> findAllByStatusPaid();
}
