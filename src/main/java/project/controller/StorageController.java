package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.converter.ResponseConverter;
import project.dto.StorageResponseDto;
import project.entity.Storage;
import project.service.StorageService;

import java.util.List;

@RestController
@RequestMapping("/api/storage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class StorageController {

    @Autowired
    private StorageService service;

    @Autowired
    private ResponseConverter<Storage, StorageResponseDto> converter;


    @PutMapping("/addQuantity")
    public ResponseEntity<StorageResponseDto> addQuantity(@RequestParam Long productId, @RequestParam Integer quantity) {
        Storage storage = service.addProductQuantity(productId, quantity);
        StorageResponseDto dto = converter.toDto(storage);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/poolQuantity")
    public ResponseEntity<StorageResponseDto> poolQuantity(@RequestParam Long productId, @RequestParam Integer quantity) {
        Storage storage = service.pool(productId, quantity);
        StorageResponseDto dto = converter.toDto(storage);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<StorageResponseDto>> getAll() {
        List<StorageResponseDto> dtos = service.getAll().stream()
                .map(converter::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<StorageResponseDto> getByProductId(@PathVariable Long productId) {
        Storage storage = service.getByProductId(productId);
        StorageResponseDto dto = converter.toDto(storage);
        return ResponseEntity.ok(dto);
    }
}







































