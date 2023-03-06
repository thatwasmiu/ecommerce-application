package com.application.ecommerce.dto;

import com.application.ecommerce.base.rest.RequestDTO;
import com.application.ecommerce.model.category.Category;
import com.application.ecommerce.model.category.CategoryType;
import com.application.ecommerce.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDTO extends RequestDTO {
    private CategoryType type;

    public Category createCategory() {

        return Category.builder()
                .type(type)
                .build();
    }
}
