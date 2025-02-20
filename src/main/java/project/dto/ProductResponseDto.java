package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductResponseDto {

    private Long id;

    private String name;

    private CategoryResponseDto category;
}
