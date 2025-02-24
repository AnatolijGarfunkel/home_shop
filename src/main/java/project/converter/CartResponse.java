package project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dto.CartItemsResponseDto;
import project.dto.CartResponseDto;
import project.entity.Cart;
import project.entity.CartItems;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartResponse implements ResponseConverter<Cart, CartResponseDto> {

    @Autowired
    private ResponseConverter<CartItems, CartItemsResponseDto> itemsConverter;

    @Override
    public CartResponseDto toDto(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<CartItemsResponseDto> itemsResponseDtos = cart.getItems().stream()
                .map(itemsConverter::toDto)
                .toList();


        for (CartItemsResponseDto item : itemsResponseDtos) {
            totalPrice = totalPrice.add(item.getItemPrice());
        }

        return new CartResponseDto(
                cart.getUser().getId(),
                itemsResponseDtos,
                totalPrice
        );
    }
}
