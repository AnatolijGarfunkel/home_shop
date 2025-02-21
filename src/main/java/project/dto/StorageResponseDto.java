package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StorageResponseDto {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantity;
}
