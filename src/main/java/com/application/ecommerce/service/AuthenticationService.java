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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RedisTemplate template;
    private final UserRepository userRepo;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(UserRegisterDto request) {

        if (userRepo.existsUserByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");

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
        // If user is valid user DaoAthProvider to set the principle to instance of UserDetails else throwing exception

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = jwtTokenUtil.generateToken(user);
        template.opsForValue().set(request.getUsername(), jwt);

        return jwt;
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        template.delete(username);
    }
}
