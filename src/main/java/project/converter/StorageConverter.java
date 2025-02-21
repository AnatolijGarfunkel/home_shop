package project.converter;

import org.springframework.stereotype.Component;
import project.dto.StorageResponseDto;
import project.entity.Storage;

@Component
public class StorageConverter implements ResponseConverter<Storage, StorageResponseDto> {


    @Override
    public StorageResponseDto toDto(Storage storage) {
        return new StorageResponseDto(storage.getId(), storage.getProducts().getId(), storage.getProducts().getName(), storage.getQuantity());
    }

}
