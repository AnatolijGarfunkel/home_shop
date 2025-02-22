package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Cart;
import project.entity.CartItems;
import project.entity.Order;
import project.entity.OrderItems;
import project.repository.OrderItemsRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsRepository repository;

    @Autowired
    private CartService cartService;


    @Override
    public List<OrderItems> create(Long userId, Order order) {
        Cart cart = cartService.getByUserId(userId);
        List<CartItems> items = cart.getItems();

        List<OrderItems> orderItems = items.stream()
                .map(
                        cartItem -> new OrderItems(
                                order,
                                cartItem.getProduct(),
                                cartItem.getQuantity(),
                                cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
                        )
                )
                .map(orderItem -> repository.save(orderItem))
                .toList();

        cartService.deleteItems(cart);
        return orderItems;
    }

    @Override
    public List<OrderItems> get() {
        return null;
    }
}







































