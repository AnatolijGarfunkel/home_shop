package project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dto.OrderResponseDto;
import project.dto.PaymentResponseDto;
import project.entity.Order;
import project.entity.Payment;

@Component
public class PaymentConverter implements ResponseConverter<Payment, PaymentResponseDto> {

    @Autowired
    private ResponseConverter<Order, OrderResponseDto> orderConverter;


    @Override
    public PaymentResponseDto toDto(Payment payment) {
        return new PaymentResponseDto(
                payment.getOrder().getUser().getId(),
                orderConverter.toDto(payment.getOrder())
        );
    }
}
