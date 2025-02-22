package project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dto.OrderItemsResponseDto;
import project.dto.OrderResponseDto;
import project.entity.Order;

import java.util.List;

@Component
public class OrderResponse implements ResponseConverter<Order, OrderResponseDto> {

    @Autowired
    private OrderItemsResponse orderItemsConverter;


    @Override
    public OrderResponseDto toDto(Order order) {
        List<OrderItemsResponseDto> items = order.getItems().stream()
                .map(item -> orderItemsConverter.toDto(item))
                .toList();

        return new OrderResponseDto(
                order.getId(),
                items,
                order.getTotalPrice()
        );
    }
}
