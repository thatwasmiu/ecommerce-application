package com.application.ecommerce.model.order;

import com.application.ecommerce.base.rest.AbstractEntity;
import com.application.ecommerce.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Long productId;
    private int quantity;
}
