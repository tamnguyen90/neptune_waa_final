package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAll();

    Product getProductById(Long productID);
    List<Product> getProductsByCategoryId(Long categoryId);
    Page<Product> listAll(int pageNum, String sortField, String sortDir);
    Page<Product> getProductsByCategoryId(Long id, int pageNum, String sortField, String sortDir);

    Page<Product> findProductsByProductNameContaining(String keyword, int pageNum, String sortField, String sortDir);
    Page<Product> findProductsByProductNameContains(String keyword, int pageNum, String sortField, String sortDir);
  //  Page<Product> findProductsByProductNameContainUppercase(String keyword, int pageNum, String sortField, String sortDir, Pageable pageable);
    List<Category> findByCategoryId(Long id);

    //For Sell module
    Product save(Product product);
    void delete(Long productId);
}
