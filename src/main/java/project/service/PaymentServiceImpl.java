package project.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.entity.Order;
import project.entity.Payment;
import project.enums.PaymentStatus;
import project.exception.AlreadyPaidException;
import project.repository.PaymentRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;


    @Transactional
    @Override
    public Payment toPay() {
        Long userId = userService.getCurrentUserId();
        Order order = orderService.getByUserIdAndPending(userId);
        if (order != null) {
            accountService.toPay(userId, order.getTotalPrice());
            Payment payment = new Payment(order, PaymentStatus.PROCESSING);
            orderService.setStatusProcessing(order);
            return repository.save(payment);
        }
        throw new AlreadyPaidException("All orders are already paid");
    }

    @Override
    public List<Payment> getAll() {
        Long userId = userService.getCurrentUserId();
        List<Order> orders = orderService.getAllByUserId(userId);
        return orders.stream()
                .map(item -> repository.findByOrder(item))
                .collect(Collectors.toList());
    }

    @Override
    public Payment getByOrderIdAndPaid(Long orderId) {
        return repository.findByOrderIdAndStatus_Paid(orderId);
    }

    @Override
    @Scheduled(initialDelay = 15, fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    public void setPay() {
        List<Payment> payments = repository.findAllByStatusProcessing();
        if (!payments.isEmpty()) {
            payments.forEach(
                    payment -> {
                        payment.setStatus(PaymentStatus.PAID);
                        repository.save(payment);
                    }
            );
        }
    }
}







































