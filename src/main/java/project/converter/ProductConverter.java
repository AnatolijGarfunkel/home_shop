package project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dto.CategoryCreateDto;
import project.dto.CategoryResponseDto;
import project.dto.ProductCreateDto;
import project.dto.ProductResponseDto;
import project.entity.Category;
import project.entity.Product;

@Component
public class ProductConverter implements Converter<Product, ProductCreateDto, ProductResponseDto> {

    @Autowired
    private Converter<Category, CategoryCreateDto, CategoryResponseDto> categoryConverter;


    @Override
    public ProductResponseDto toDto(Product product) {
        CategoryResponseDto categoryDto = categoryConverter.toDto(product.getCategory());
        return new ProductResponseDto(product.getId(), product.getName(), categoryDto);
    }

    @Override
    public Product toEntity(ProductCreateDto dto) {
        return new Product(dto.getName(), dto.getPrice());
    }
}
