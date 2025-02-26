package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderResponseDto {

    private Long id;

    private List<OrderItemsResponseDto> items;

    private BigDecimal totalPrice;

    private OrderStatus status;
}
