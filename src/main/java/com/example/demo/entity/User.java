package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String name;
    private String lastname;
    private String nickname;
    @ManyToMany
            @JoinTable(
                    name = "user_roles",
                    joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private List<Role> roles;
    private String pass;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "user")
    private List<SavedFlights> savedFlights;


    private String passPlain;
    @ManyToMany
            @JoinTable(
                    name = "user_creditCard",
                    joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name="creditCard_id")
            )
    private List<CreditCard> creditCards;

}
