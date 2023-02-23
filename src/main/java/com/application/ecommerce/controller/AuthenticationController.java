package com.application.ecommerce.controller;

import com.application.ecommerce.service.AuthenticationService;
import com.application.ecommerce.dto.user.UserLoginDto;
import com.application.ecommerce.dto.user.UserRegisterDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/signout")
    public ResponseEntity<Object> logoutUser() {
        service.logout();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(
                                    @RequestBody UserRegisterDto registerRequest,
                                    HttpServletResponse response
    ) {
        String token = service.register(registerRequest);
        Cookie cookie = new Cookie("Token", token);
        cookie.setMaxAge(24*60*60);
//        cookie.setSecure(true);    https protocol only, the connection need to be encrypted
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(
                                    @RequestBody UserLoginDto loginRequest,
                                    HttpServletResponse response
    ) {
        String token = service.authenticate(loginRequest);

        Cookie cookie = new Cookie("Token", token);
        cookie.setMaxAge(24*60*60);
//        cookie.setSecure(true);    https protocol only, the connection need to be encrypted
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return ResponseEntity.accepted().build();
    }
}
