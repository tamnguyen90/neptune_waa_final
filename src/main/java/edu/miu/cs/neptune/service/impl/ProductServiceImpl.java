package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.repository.CategoryRepository;
import edu.miu.cs.neptune.repository.ProductRepository;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<Product> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc")? Sort.by(sortField).ascending():Sort.by(sortField).descending());
        return productRepository.findAll(pageable);

    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


}
