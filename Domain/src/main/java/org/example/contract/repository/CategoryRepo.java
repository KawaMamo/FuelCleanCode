package org.example.contract.repository;

import org.example.model.Category;

public interface CategoryRepo {
    Category save(Category category);

    Category findById(Long id);

    void delete(Category category);
}
