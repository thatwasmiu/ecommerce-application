package com.application.ecommerce.repository;

import com.application.ecommerce.model.order.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPurchaseRepo extends JpaRepository<ProductPurchase, Long> {
}
