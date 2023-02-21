package com.application.ecommerce.model;

import com.application.ecommerce.base.rest.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity {

    @Enumerated(value = EnumType.STRING)
    private CategoryType type;
}
