package com.application.ecommerce.model.product;

import com.application.ecommerce.base.rest.AbstractEntity;
import com.application.ecommerce.model.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
//@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
@Table(indexes = @Index(name = "product_name_index", columnList = "name ASC"))
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    private Long id;

    @NotNull
    private String name;

    private Long categoryId;

    private String imgUrl;

    private String description;

    @NotNull
    private Double price;

}
