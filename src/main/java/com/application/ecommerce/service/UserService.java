package com.application.ecommerce.service;

import com.application.ecommerce.exception.ResourceNotFoundException;
import com.application.ecommerce.config.AppUserDetails;
import com.application.ecommerce.model.user.User;
import com.application.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames={"users"})
public class UserService implements UserDetailsService{

    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repo.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        return new AppUserDetails(user);
    }

    public User getUserByUsername(String username) {
        return repo.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Cacheable
    public List<User> getAllUser() {
        return repo.findAll();
    }

    public User upSertUser(User user) {
        return repo.save(user);
    }

    public void removeUserById(Long id) {
        repo.deleteById(id);
    }
}
