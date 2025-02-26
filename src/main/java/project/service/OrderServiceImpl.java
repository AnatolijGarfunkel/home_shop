package project.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.entity.Order;
import project.entity.OrderItems;
import project.entity.User;
import project.enums.OrderStatus;
import project.exception.CartIsEmptyException;
import project.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartService cartService;


    @Override
    @Transactional
    public Order create() {
        if (!cartService.getByCurrentUser().getItems().isEmpty()) {
            User currentUser = getCurrentUser();
            Order order = new Order(currentUser);
            Order save = repository.save(order);
            List<OrderItems> orderItems = orderItemsService.create(currentUser.getId(), save);

            BigDecimal totalPrice = orderItems.stream()
                    .map(OrderItems::getItemPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            save.setTotalPrice(totalPrice);
            save = repository.save(save);
            save.setItems(orderItems);
            return save;
        }
        throw new CartIsEmptyException("There are no selected products in your cart");
    }

    @Override
    public List<Order> get() {
        Long userId = userService.getCurrentUserId();
        return getAllByUserId(userId);
    }

    @Override
    public Order getByUserIdAndPending(Long userId) {
        return repository.findByUserIdAndStatusPending(userId).orElse(null);
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public void setStatusProcessing(Order order) {
        order.setStatus(OrderStatus.PROCESSING);
        repository.save(order);
    }

    @Override
    @Scheduled(initialDelay = 15, fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    public void checkPayments() {
        List<Order> orders = repository.findAllByStatusProcessing();
        if (!orders.isEmpty()) {
            orders.stream()
                    .map(Order::getId)
                    .map(id -> paymentService.getByOrderIdAndPaid(id))
                    .map(payment -> payment.getOrder().getId())
                    .map(id -> repository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .peek(order -> order.setStatus(OrderStatus.PAID))
                    .forEach(order -> repository.save(order));
        }

    }

    @Override
    public List<Order> getAllByPaid() {
        return repository.findAllByStatusPaid();
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    @Scheduled(initialDelay = 38, fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    public void checkPaid() {
        List<Order> orders = repository.findAllByStatusSent();
        if (!orders.isEmpty()) {
            orders.stream()
                    .peek(order -> order.setStatus(OrderStatus.DELIVERED))
                    .forEach(order -> repository.save(order));
        }
    }


    private Long getCurrentUserId() {
        return userService.getCurrentUserId();
    }

    private User getCurrentUser() {
        return userService.getCurrentUser();
    }

}







































