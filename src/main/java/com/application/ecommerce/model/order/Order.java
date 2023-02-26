package com.application.ecommerce.model.order;

import com.application.ecommerce.base.rest.AbstractEntity;
import com.application.ecommerce.model.user.User;
import com.application.ecommerce.model.voucher.Voucher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product_order")
public class  Order extends AbstractEntity {

    @ManyToOne
    private User owner;

    @NotNull
    private LocalDate purchaseDate;

    @OneToMany
    private List<ProductPurchase> productPurchases;

    @OneToMany
    private List<Voucher> vouchers;

    private Double deliveryCost;

    private Double totalPrice;

    @Column(columnDefinition = "varchar(10) default 'PENDING'")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
}
