package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import project.converter.Converter;
import project.dto.CategoryCreateDto;
import project.dto.CategoryResponseDto;
import project.entity.Category;
import project.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private Converter<Category, CategoryCreateDto, CategoryResponseDto> converter;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryCreateDto dto) {
        Category entity = converter.toEntity(dto);
        Category category = service.create(entity);
        CategoryResponseDto responseDto = converter.toDto(category);
        ResponseEntity<CategoryResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> list = service.getAll();
        ResponseEntity<List<Category>> response = ResponseEntity.ok(list);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Category category = service.getById(id);
        ResponseEntity<Object> response = ResponseEntity.ok(category);
        return response;
    }
}
