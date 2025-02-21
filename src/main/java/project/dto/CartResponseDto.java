package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class CartResponseDto {

    private Long id;

    private Long user_id;

    private List<CartItemsResponseDto> cartItems;

    private BigDecimal totalPrice;
}
