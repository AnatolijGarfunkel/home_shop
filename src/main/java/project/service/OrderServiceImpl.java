package project.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Order;
import project.entity.OrderItems;
import project.entity.User;
import project.enums.OrderStatus;
import project.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemsService orderItemsService;


    @Override
    @Transactional
    public Order create() {
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

    @Override
    public List<Order> get() {
        Long userId = userService.getCurrentUserId();
        return getAllByUserId(userId);
    }

    @Override
    public Order getByUserIdAndPending(Long userId) {
        return repository.findByUserIdAndStatusContains(userId, OrderStatus.PENDING);
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

    private Long getCurrentUserId() {
        return userService.getCurrentUserId();
    }

    private User getCurrentUser() {
        return userService.getCurrentUser();
    }
}







































