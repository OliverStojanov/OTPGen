package com.example.otpgen.repository;

import com.example.otpgen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    public Optional<User> findByEmailEqualsAndPasswordEquals(String email, String password);

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailEquals(String email);
}
