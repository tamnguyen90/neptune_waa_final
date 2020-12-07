package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAll();
    List<Product> findByName(String name);
//    List<Product> getProductsByCategory(String category);
//
//    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
//
//    Product getProductById(String productID);
}
