package com.application.ecommerce.dto;

import com.application.ecommerce.jwt.JwtToken;
import com.application.ecommerce.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private JwtToken token;
    private User user;
}
