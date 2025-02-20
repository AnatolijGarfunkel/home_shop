package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.converter.Converter;
import project.dto.ProductCreateDto;
import project.dto.ProductResponseDto;
import project.entity.Category;
import project.entity.Product;
import project.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private Converter<Product, ProductCreateDto, ProductResponseDto> converter;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductCreateDto dto) {
        Product entity = converter.toEntity(dto);
        Category category = service.getCategoryByCategoryId(dto.getCategoryId());
        entity.setCategory(category);
        Product product = service.create(entity);
        ProductResponseDto responseDto = converter.toDto(product);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = service.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = service.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}







































