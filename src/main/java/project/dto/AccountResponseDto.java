package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class AccountResponseDto {

    private Long id;

    private UserResponseDto user;

    private BigDecimal amount;
}
