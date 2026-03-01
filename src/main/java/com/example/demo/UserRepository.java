package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long userId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.addressesOfUser WHERE u.id = :id")
    Optional<User> findUserWithAddresses(@Param("id") Long id);
}
