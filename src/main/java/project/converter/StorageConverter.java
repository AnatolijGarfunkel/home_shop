package project.converter;

import org.springframework.stereotype.Component;
import project.dto.StorageCreateDto;
import project.dto.StorageResponseDto;
import project.entity.Storage;

@Component
public class StorageConverter implements Converter<Storage, StorageCreateDto, StorageResponseDto> {


    @Override
    public StorageResponseDto toDto(Storage storage) {
        return new StorageResponseDto(storage.getId(), storage.getProducts().getId(), storage.getProducts().getName(), storage.getQuantity());
    }

    @Override
    public Storage toEntity(StorageCreateDto storageCreateDto) {
        return null;
    }
}
