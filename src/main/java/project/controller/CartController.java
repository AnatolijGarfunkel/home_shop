package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.converter.ResponseConverter;
import project.dto.CartResponseDto;
import project.entity.Cart;
import project.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService service;

    @Autowired
    private ResponseConverter<Cart, CartResponseDto> converter;


    @PutMapping
    public ResponseEntity<CartResponseDto> add(
            @RequestParam Long productId,
            @RequestParam(required = false, defaultValue = "1") Integer quantity) {
        Cart cart = service.add(productId, quantity);
        CartResponseDto dto = converter.toDto(cart);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<CartResponseDto> get() {
        Cart cart = service.getByCurrentUser();
        CartResponseDto dto = converter.toDto(cart);
        return ResponseEntity.ok(dto);
    }
}
