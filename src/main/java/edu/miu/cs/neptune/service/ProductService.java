package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAll();
    List<Product> findByName(String name);
//    List<Product> getProductsByCategory(String category);
//
//    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
//
    Product getProductById(Long productID);
    List<Product> getProductsByCategoryId(Long categoryId);
    Page<Product> listAll(int pageNum, String sortField, String sortDir);

    Product save(Product product);
}
