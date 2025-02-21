package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CartItemsResponseDto {

    private Long id;

    private Long cart_id;

    private Long product_id;

    private String name;

    private BigDecimal productPrice;

    private Integer quantity;

    private BigDecimal itemPrice;
}
