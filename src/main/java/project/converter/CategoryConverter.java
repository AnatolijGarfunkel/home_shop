package project.converter;

import org.springframework.stereotype.Component;
import project.dto.CategoryCreateDto;
import project.dto.CategoryResponseDto;
import project.entity.Category;

@Component
public class CategoryConverter implements Converter<Category, CategoryCreateDto, CategoryResponseDto> {


    @Override
    public CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    @Override
    public Category toEntity(CategoryCreateDto dto) {
        return new Category(dto.getName());
    }
}
