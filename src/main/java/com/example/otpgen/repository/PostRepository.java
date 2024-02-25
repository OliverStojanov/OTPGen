package com.example.otpgen.repository;

import com.example.otpgen.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
