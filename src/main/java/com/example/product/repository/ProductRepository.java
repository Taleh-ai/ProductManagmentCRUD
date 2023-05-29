package com.example.product.repository;

import com.example.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    List<ProductEntity> findProductsByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("SELECT p FROM ProductEntity p WHERE p.quantity =:stock")
    List<ProductEntity> findProductsByStock(@Param("stock") int stock);


    ProductEntity findProductEntityById(Long id);
    ProductEntity findByName(String name);
    boolean existsByName(String name);

}
