package com.application.ecommerce.model.product;

import com.application.ecommerce.model.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    @Size(min = 1, max = 30, message = "The name is in the range of 1 to 30 character")
    private String name;

    @Size(max = 1000, message = "The maximum number of character is 1000")
    private String description;

    @NotNull
    private Double price;
}
