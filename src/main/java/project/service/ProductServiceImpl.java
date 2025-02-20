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
        return repository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Product with id " + id + " not found")
                );
    }

    @Override
    public Category getCategoryByCategoryId(Long categoryId) {
        return categoryService.getById(categoryId);
    }


}
