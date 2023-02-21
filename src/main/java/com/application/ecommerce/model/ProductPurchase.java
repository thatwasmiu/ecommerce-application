package com.application.ecommerce.model;

import com.application.ecommerce.base.rest.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPurchase extends AbstractEntity {

    @OneToOne
    private Product product;
    private int quantity;
    private Double totalPrice;
}
