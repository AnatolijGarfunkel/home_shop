package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.converter.ResponseConverter;
import project.dto.PaymentResponseDto;
import project.entity.Payment;
import project.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private ResponseConverter<Payment, PaymentResponseDto> converter;


    @PostMapping
    public ResponseEntity<PaymentResponseDto> toPay() {
        Payment payment = service.toPay();
        PaymentResponseDto dto = converter.toDto(payment);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAll() {
        List<Payment> payments = service.getAll();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
