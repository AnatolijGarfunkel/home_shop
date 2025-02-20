package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Category;
import project.entity.Product;
import project.exception.NotFoundException;
import project.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;


    @Override
    public Product create(Product product) {
        Product save = repository.save(product);
        return save;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = repository.findAll();
        return products;
    }

    @Override
    public Product getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Product with id " + id + " not found")
                );

        return product;
    }

    @Override
    public Category getCategoryByCategoryId(Long categoryId) {
        Category category = categoryService.getById(categoryId);
        return category;
    }


}
