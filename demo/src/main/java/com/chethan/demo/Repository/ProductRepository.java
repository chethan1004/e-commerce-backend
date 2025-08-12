package com.chethan.demo.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chethan.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:description IS NULL OR LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%'))) AND " +
           "(:price IS NULL OR p.price = :price) AND " +
           "(:quantity IS NULL OR p.quantity = :quantity)")
    List<Product> searchProducts(@Param("name") String name,
                                 @Param("description") String description,
                                 @Param("price") Double price,
                                 @Param("quantity") Integer quantity);
}