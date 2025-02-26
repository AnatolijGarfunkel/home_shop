package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.converter.OrderResponse;
import project.dto.OrderResponseDto;
import project.entity.Order;
import project.service.OrderService;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        List<Order> orders = service.get();
        List<OrderResponseDto> dtos = orders.stream()
                .map(item -> converter.toDto(item))
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}







































