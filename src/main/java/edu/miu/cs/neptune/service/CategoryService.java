package edu.miu.cs.neptune.service;


import edu.miu.cs.neptune.domain.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAll();

    public Category save(Category category);
}
