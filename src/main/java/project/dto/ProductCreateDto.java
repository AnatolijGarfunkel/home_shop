package project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class ProductCreateDto {

    private String name;

    private Long categoryId;

    private BigDecimal price;
}
