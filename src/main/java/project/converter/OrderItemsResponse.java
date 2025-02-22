package project.converter;

import org.springframework.stereotype.Component;
import project.dto.OrderItemsResponseDto;
import project.entity.OrderItems;

@Component
public class OrderItemsResponse implements ResponseConverter<OrderItems, OrderItemsResponseDto> {


    @Override
    public OrderItemsResponseDto toDto(OrderItems orderItem) {

        return new OrderItemsResponseDto(
                orderItem.getProduct().getName(),
                orderItem.getProduct().getPrice(),
                orderItem.getQuantity(),
                orderItem.getItemPrice()
        );
    }
}
