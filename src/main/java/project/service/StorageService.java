package project.service;

import project.entity.Product;
import project.entity.Storage;

import java.util.List;

public interface StorageService {

    Storage addProduct(Product product, Integer quantity);

    Storage addProductQuantity(Long productId, Integer quantity);

    Storage pool(Long productId, Integer quantity);

    Product getProductByProductId(Long id);

    List<Storage> getAll();

    Storage getByProductId(Long id);

    void toReduceProductQuantity();

    void toCheckAvailibility(Long productId, Integer quantity);

    void deleteByProductId(Long productId);
}
