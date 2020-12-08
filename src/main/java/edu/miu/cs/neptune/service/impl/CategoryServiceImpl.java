package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.exception.CategoryException;
import edu.miu.cs.neptune.repository.CategoryRepository;
import edu.miu.cs.neptune.repository.ProductRepository;
import edu.miu.cs.neptune.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return Util.iterableToCollection(categoryRepository.findAll());
    }

    @Override
    public Category save(Category category) {
//        Optional<Category> foundCategory = categoryRepository.findById(category.getCategoryId());
//
//        if(foundCategory.isPresent()) {
//            throw new CategoryException("Duplicate category ID");
//        }

        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        if(categoryRepository.countProductsByCategoryId(id)>0) {
            throw new CategoryException("There are products belong to the category");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
