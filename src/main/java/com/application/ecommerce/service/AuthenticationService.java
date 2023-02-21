package com.application.ecommerce.service;

import com.application.ecommerce.repository.UserRepository;
import com.application.ecommerce.dto.user.UserLoginDto;
import com.application.ecommerce.dto.user.UserRegisterDto;
import com.application.ecommerce.exception.ResourceNotFoundException;
import com.application.ecommerce.config.AppUserDetails;
import com.application.ecommerce.jwt.JwtTokenUtil;
import com.application.ecommerce.model.Role;
import com.application.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(UserRegisterDto registerRequest) {

        if (userRepo.findByUsername(registerRequest.getUsername()).isPresent())
            throw new RuntimeException();

        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .username(registerRequest.getUsername())
                .role(Role.CUSTOMER)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepo.save(user);
        return jwtTokenUtil.generateToken(new AppUserDetails(user));
    }

    public String authenticate(UserLoginDto loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
        );
        User user = userRepo.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        return jwtTokenUtil.generateToken(new AppUserDetails(user));

    }
}
