package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Category;
import project.exception.NotFoundException;
import project.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;


    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Category with id " + id + " not found")
                );
    }
}
