package com.example.otpgen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    public Long id;
    @ManyToOne
    public User user;
    public String title;
    public String content;

}
