package com.application.ecommerce.repository;

import com.application.ecommerce.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
    Optional<User> findUserByUsername(String username);

    boolean existsUserByUsername(String username);

    Optional<User> findByUsernameEquals(String username);
}
