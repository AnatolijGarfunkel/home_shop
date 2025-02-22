package project.converter;

import org.springframework.stereotype.Component;
import project.dto.CartItemsResponseDto;
import project.entity.CartItems;

import java.math.BigDecimal;

@Component
public class CartItemsResponse implements ResponseConverter<CartItems, CartItemsResponseDto> {


    @Override
    public CartItemsResponseDto toDto(CartItems cartItems) {

        BigDecimal price = cartItems.getProduct().getPrice();
        BigDecimal itemPrice = price.multiply(BigDecimal.valueOf(cartItems.getQuantity()));
        return new CartItemsResponseDto(
                cartItems.getProduct().getName(),
                cartItems.getProduct().getPrice(),
                cartItems.getQuantity(),
                itemPrice
                );
    }
}
