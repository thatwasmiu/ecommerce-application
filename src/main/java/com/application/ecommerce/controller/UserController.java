package com.application.ecommerce.controller;

import com.application.ecommerce.dto.user.UserUpdateDTO;
import com.application.ecommerce.model.user.User;
import com.application.ecommerce.repository.UserRepository;
import com.application.ecommerce.service.UserService;
import com.application.ecommerce.base.rsql.AppRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    UserService service;

    UserRepository dao;

    PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> retrieveAllUser() {
        return service.getAllUser();
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('CUSTOMER') and #username == principal.username")
    public ResponseEntity<User> retrieveUserByUsername(@PathVariable String username) {
        User user = service.getUserByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = service.upSertUser(user);

        URI path = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUser.getUsername())
                .toUri();
        return ResponseEntity.created(path).build();
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasAuthority('CUSTOMER') and #username == principal.username")
    public ResponseEntity<User> updateUserDetails(@PathVariable String username,
                                                  @RequestBody UserUpdateDTO dto) {
        User user = service.getUserByUsername(username);
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        service.upSertUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        service.removeUserById(id);
        return new ResponseEntity<>("Successfully Delete User!", HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<User> findAllByRsql(@RequestParam("search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<User> spec = rootNode.accept(new AppRsqlVisitor<>());
        return dao.findAll(spec);
    }
}
