package com.application.ecommerce.controller;

import com.application.ecommerce.jwt.JwtToken;
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
        return new ResponseEntity<>("Logout from the system!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtToken> registerUser(
                                    @RequestBody UserRegisterDto registerRequest,
                                    HttpServletResponse response
    ) {
        JwtToken token = service.register(registerRequest);


        Cookie cookie = new Cookie("Token", token.getToken());
        cookie.setMaxAge(token.getExprDate().intValue());
//        cookie.setSecure(true);    https protocol only, the connection need to be encrypted
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> authenticateUser(
                                    @RequestBody UserLoginDto loginRequest,
                                    HttpServletResponse response
    ) {
        JwtToken token = service.authenticate(loginRequest);

        Cookie cookie = new Cookie("Token", token.getToken());
        cookie.setMaxAge(token.getExprDate().intValue());
//        cookie.setSecure(true);    https protocol only, the connection need to be encrypted
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
    }

    @GetMapping("/rando")
    public String getrando() {
        return "Rando";
    }
}
