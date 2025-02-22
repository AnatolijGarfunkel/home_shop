package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderItemsResponseDto {

    private String productName;

    private BigDecimal productPrice;

    private Integer quantity;

    private BigDecimal itemPrice;
}
