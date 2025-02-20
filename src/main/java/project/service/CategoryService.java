package project.service;

import project.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    List<Category> getAll();

    Category getById(Long id);
}
