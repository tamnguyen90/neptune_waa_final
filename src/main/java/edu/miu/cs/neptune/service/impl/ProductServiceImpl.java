package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.repository.CategoryRepository;
import edu.miu.cs.neptune.repository.ProductRepository;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    public Product getProductById(Long productID) {
        return productRepository.getProductByProductId(productID);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

    @Override
    public Page<Product> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc")? Sort.by(sortField).ascending():Sort.by(sortField).descending());
        return productRepository.findAll(pageable);

    }

    @Override
    public Slice<Product> getProductsByCategoryId(Long id, int start, int end, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(start - 1, 5,
                sortDir.equals("asc")? Sort.by(sortField).ascending():Sort.by(sortField).descending());

        return productRepository.getProductsByCategoryId(id, pageable);
    }

    @Override
    public List<Product> findProductsByProductNameContaining(String keyword) {
        return productRepository.findProductsByProductNameContaining(keyword);
    }


    @Override
    public Page<Product> findProductsByProductNameContains(String keyword, int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum-1, 5,
                sortDir.equals("asc")?Sort.by(sortField).ascending():Sort.by(sortField).descending());
        return productRepository.findProductsByProductNameContains(keyword,pageable);
    }

    @Override
    public List<Category> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


}
