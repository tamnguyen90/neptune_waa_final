package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.domain.Product;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAll();
//    Page<Product> listAll(int pageNum, String sortField, String sortDir);

//    @Query(value = "SELECT p FROM Product p WHERE UPPER(p.productName) = UPPER(?1)")
//    @Query(value = "SELECT p FROM Product p WHERE p.productName like ?1")
    Page<Product> findProductsByProductNameContains(String name, Pageable pageable);
    //Page<Product> findProductsByProductNameContainUppercase(String name, Pageable pageable);
//    Slice<Product> getProductsByCategoryId(Long id, Pageable pageable);
    Product getProductByProductId(Long id);



//    @Query("select p from Product p where p.productId = :id")
////    SELECT * FROM PRODUCT WHERE PRODUCTS_CATEGORY_ID =1;
//    List<Product> getProductsByCategoryID(@Param("id") long id);

    List<Product> getProductsByCategoryId(Long id);
    Page<Product> getProductsByCategoryId(Long id, Pageable pageable);
//    @Query(value = "SELECT p FROM Product p WHERE p.productName like %:keyword%")
    Page<Product> findProductsByProductNameContaining(String name, Pageable pageable);
    List<Category> findByCategoryId(Long id);

    //For seller module
    @Query(value = "SELECT p FROM Product p WHERE p.seller.userId = :sellerId")
    List<Product> findProductsBySeller(Long sellerId);





}
