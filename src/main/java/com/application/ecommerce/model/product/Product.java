package com.application.ecommerce.model.product;

import com.application.ecommerce.base.rest.AbstractEntity;
import com.application.ecommerce.model.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Size(min = 1, max = 30, message = "The name is in the range of 1 to 30 character")
    private String name;

    @Size(max = 1000, message = "The maximum number of character is 1000")
    private String description;

    @NotNull
    private Double price;
}
