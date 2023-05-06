package com.application.ecommerce.model.category;

import com.application.ecommerce.base.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity{

    private String type;

}
