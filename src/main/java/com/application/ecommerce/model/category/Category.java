package com.application.ecommerce.model.category;

import com.application.ecommerce.base.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CategoryType type;

}
