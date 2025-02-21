package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Product;
import project.entity.Storage;
import project.exception.NotFoundException;
import project.repository.StorageRepository;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository repository;

    @Autowired
    private ProductService productService;


    @Override
    public Storage addProduct(Product product, Integer quantity) {
        Storage storage = new Storage(product, quantity);
        return repository.save(storage);
    }

    @Override
    public Storage addProductQuantity(Long productId, Integer quantity) {
        Storage storage = getByProductId(productId);
        storage.setQuantity(storage.getQuantity() + quantity);
        return repository.save(storage);
    }

    @Override
    public Storage pool(Long productId, Integer quantity) {
        Storage storage = getByProductId(productId);
        storage.setQuantity(storage.getQuantity() - quantity);
        return repository.save(storage);
    }

    @Override
    public Product getProductByProductId(Long id) {
        return productService.getById(id);
    }

    @Override
    public List<Storage> getAll() {
        return repository.findAll();
    }

    @Override
    public Storage getByProductId(Long productId) {
        return repository.findByProductId(productId)
                .orElseThrow(
                        () -> new NotFoundException("Product with id " + productId + " not found in storage")
                );
    }
}
