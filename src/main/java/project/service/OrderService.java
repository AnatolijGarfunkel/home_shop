package project.service;

import project.entity.Order;

import java.util.List;

public interface OrderService {

    Order create();

    List<Order> get();

    Order getByUserIdAndPending(Long userId);

    List<Order> getAllByUserId(Long userId);

    void setStatusProcessing(Order order);
}
