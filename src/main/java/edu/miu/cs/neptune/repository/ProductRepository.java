package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAll();
    List<Product> findProductsByProductName(String name);
    List<Product> findByCategories(String categoryName);
//
//    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
//
    Product getProductByProductId(String Id);

}
