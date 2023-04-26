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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    @Size(min = 1, max = 30, message = "The name is in the range of 1 to 30 character")
    private String name;

    @Size(max = 1000, message = "The maximum number of character is 1000")
    private String description;

    @NotNull
    private Double price;

    @JsonIgnore
    private boolean deleted = Boolean.FALSE;
}
