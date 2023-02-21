package com.application.ecommerce.controller;

import com.application.ecommerce.service.AuthenticationService;
import com.application.ecommerce.dto.user.UserLoginDto;
import com.application.ecommerce.dto.user.UserRegisterDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

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

        return ResponseEntity.ok(token);
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
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }


}
