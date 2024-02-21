package com.example.otpgen.repository;

import com.example.otpgen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<Object> findByEmailAndPassword(String email, String password);
    public Optional<Object> findByEmailEqualsAndPasswordEquals(String email, String password);

    Optional<Object> findByEmail(String email);
}
