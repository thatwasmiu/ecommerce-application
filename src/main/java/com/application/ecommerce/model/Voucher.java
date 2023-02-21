package com.application.ecommerce.model;

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

    private Double discountPercent;

    private LocalDate expirationDate;
}
