package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Order;
import project.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByOrder(Order order);
}
