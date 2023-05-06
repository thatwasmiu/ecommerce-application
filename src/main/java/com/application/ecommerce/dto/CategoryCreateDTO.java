package com.application.ecommerce.dto;

import com.application.ecommerce.base.rest.RequestDTO;
import com.application.ecommerce.model.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryCreateDTO extends RequestDTO {

    public Category createCategory() {
        return new Category("");
    }
}
