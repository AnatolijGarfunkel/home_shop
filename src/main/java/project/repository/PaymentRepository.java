package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Order;
import project.entity.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByOrder(Order order);

    @Query(
            nativeQuery = true,
            value = "select * from payments where order_id = :orderId and status = 'PAID'"
    )
    Payment findByOrderIdAndStatus_Paid(Long orderId);

    @Query(
            nativeQuery = true,
            value = "select * from payments where status = 'PROCESSING'"
    )
    List<Payment> findAllByStatusProcessing();
}
