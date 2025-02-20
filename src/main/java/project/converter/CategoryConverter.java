package project.converter;

import org.springframework.stereotype.Component;
import project.dto.CategoryCreateDto;
import project.dto.CategoryResponseDto;
import project.entity.Category;

@Component
public class CategoryConverter implements Converter<Category, CategoryCreateDto, CategoryResponseDto> {


    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto(category.getId(), category.getName());
        return dto;
    }

    @Override
    public Category toEntity(CategoryCreateDto dto) {
        Category category = new Category(dto.getName());
        return category;
    }
}
