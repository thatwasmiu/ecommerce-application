package com.application.ecommerce.dto;

import com.application.ecommerce.base.rest.RequestDTO;
import com.application.ecommerce.model.order.Order;
import com.application.ecommerce.model.order.OrderStatus;
import com.application.ecommerce.model.order.ProductPurchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO extends RequestDTO {

    private Long customerId;
    private Long voucherId;
    private List<ProductPurchase> products;
    private Double orderPrice;

    public OrderCreateDTO(List<ProductPurchase> products) {
        super();
        this.products = products;
    }

    public static Order createOrderFromDTO(OrderCreateDTO dto) {
        return Order.builder()
                .purchaseDate(LocalDate.now())
                .voucherId(dto.voucherId)
                .orderPrice(dto.orderPrice)
                .status(OrderStatus.PENDING)
                .build();
    }
}
