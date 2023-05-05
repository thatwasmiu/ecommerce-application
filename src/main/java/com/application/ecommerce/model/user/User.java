package com.application.ecommerce.model.user;

import com.application.ecommerce.base.rest.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15, message = "Me test firstname")
    private  String email;

    @Size(min = 1, max = 10)
    private String username;

    @JsonIgnore
    @Size(max = 10000, message = "Me test password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
