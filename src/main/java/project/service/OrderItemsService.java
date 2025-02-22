package project.service;

import project.entity.Order;
import project.entity.OrderItems;

import java.util.List;

public interface OrderItemsService {

    List<OrderItems> create(Long userId, Order order);

    List<OrderItems> get();
}
