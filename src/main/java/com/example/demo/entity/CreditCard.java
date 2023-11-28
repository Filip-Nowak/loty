package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String number;
    private String expiry;
    private String ccv;
    @ManyToMany(mappedBy = "creditCards")
    private List<User> users;
}
