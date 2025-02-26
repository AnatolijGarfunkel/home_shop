package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResponseDto {

    private Long userId;

    private OrderResponseDto order;
}
