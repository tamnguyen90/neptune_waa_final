package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.repository.CategoryRepository;
import edu.miu.cs.neptune.repository.ProductRepository;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> getAll() {
        return Util.iterableToCollection(productRepository.findAll());
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findProductsByProductName(name);
    }


//    @Override
//    public List<Product> getProductsByCategory(String category) {
//        return productRepository.findByCategories(category);
//    }


//
//    @Override
//    public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
//        return productRepository.getProductsByFilter(filterParams);
//    }
//
    @Override
    public Product getProductById(Long productID) {
        return productRepository.getProductByProductId(productID);
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.getProductsByCategoryID(categoryId);
    }


}
