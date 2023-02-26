package com.application.ecommerce.model.voucher;

import com.application.ecommerce.base.rest.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Voucher extends AbstractEntity {

    private String name;

    private String description;

    private Double discountPercent;

    private LocalDate expirationDate;
}
