package com.application.ecommerce.repository;

import com.application.ecommerce.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

    @Query(nativeQuery = true, value = "SELECT * from user u where cast(u.username as binary) = cast(?1 as binary)")
    Optional<User> findUserByUsername(String username);

//    @Query(nativeQuery = true, value = "select exists(select u.id from user u where cast(u.username as binary) = cast(?1 as binary) limit 1)")
    boolean existsUserByUsername(String username);

    Optional<User> findByUsernameEquals(String username);
}
