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
    Slice<Product> getProductsByCategoryId(Long id, int start, int end, String sortField, String sortDir);

    List<Product> findProductsByProductNameContaining(String keyword);
    Page<Product> findProductsByProductNameContains(String keyword, int pageNum, String sortField, String sortDir);
    List<Category> findByCategoryId(Long id);

    //For Sell module
    Product save(Product product);
    void delete(Long productId);
}
