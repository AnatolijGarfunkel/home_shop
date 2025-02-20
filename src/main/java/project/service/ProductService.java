package project.service;

import project.entity.Category;
import project.entity.Product;
import java.util.List;

public interface ProductService {

    Product create(Product product);

    List<Product> getAll();

    Product getById(Long id);

    Category getCategoryByCategoryId(Long categoryId);
}
