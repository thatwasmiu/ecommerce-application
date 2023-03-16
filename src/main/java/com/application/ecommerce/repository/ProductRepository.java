package com.application.ecommerce.repository;

import com.application.ecommerce.model.product.Product;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE product SET deleted = true WHERE id=?1")
    @Override
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE product", nativeQuery = true)
    void truncateTable();
}
