package project.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Order;
import project.entity.OrderItems;
import project.entity.User;
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
    public Order get() {
        return null;
    }

    private Long getCurrentUserId() {
        return userService.getCurrentUserId();
    }

    private User getCurrentUser() {
        return userService.getCurrentUser();
    }
}







































