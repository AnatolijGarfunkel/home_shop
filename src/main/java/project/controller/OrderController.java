package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.converter.OrderResponse;
import project.dto.OrderResponseDto;
import project.entity.Order;
import project.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderResponse converter;


    @PostMapping
    public ResponseEntity<OrderResponseDto> create() {
        Order order = service.create();
        OrderResponseDto dto = converter.toDto(order);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
