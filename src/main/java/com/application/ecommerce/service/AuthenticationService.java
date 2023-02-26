package com.application.ecommerce.service;

import com.application.ecommerce.repository.UserRepository;
import com.application.ecommerce.dto.user.UserLoginDto;
import com.application.ecommerce.dto.user.UserRegisterDto;
import com.application.ecommerce.exception.ResourceNotFoundException;
import com.application.ecommerce.config.AppUserDetails;
import com.application.ecommerce.jwt.JwtTokenUtil;
import com.application.ecommerce.model.user.Role;
import com.application.ecommerce.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RedisTemplate template;
    private final UserRepository userRepo;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(UserRegisterDto request) {

        if (userRepo.findByUsername(request.getUsername()).isPresent())
            throw new RuntimeException();

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .role(Role.CUSTOMER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepo.save(user);
        String jwt = jwtTokenUtil.generateToken(new AppUserDetails(user));
        template.opsForValue().set(request.getUsername(), jwt);

        return jwt;
    }

    public String authenticate(UserLoginDto request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );

        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        String jwt = jwtTokenUtil.generateToken(new AppUserDetails(user));
        template.opsForValue().set(request.getUsername(), jwt);

        return jwt;
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);
        template.delete(username);
    }
}
