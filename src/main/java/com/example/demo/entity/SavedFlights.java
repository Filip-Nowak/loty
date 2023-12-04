package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SavedFlights {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;
    //Flight flight;
}
