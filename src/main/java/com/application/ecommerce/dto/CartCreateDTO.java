package com.application.ecommerce.dto;

import com.application.ecommerce.base.rest.RequestDTO;
import com.application.ecommerce.model.Order;
import com.application.ecommerce.model.ProductPurchase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CartCreateDTO extends RequestDTO {

    private Long customerId;

    private List<ProductPurchase> products;

    public CartCreateDTO(List<ProductPurchase> products) {
        super();
        this.products = products;
    }

    public static Order createCartFromDTO(CartCreateDTO dto) {
        return Order.builder()
                .purchaseDate(LocalDate.now())
                .productPurchases(dto.getProducts())
                .build();
    }
}
