package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="arrive")
    private Airport arriveAirport;
    private long arriveDateTime;
    @ManyToOne
    @JoinColumn(name="departure")
    private Airport departureAirport;
    private long departureDateTime;
    private int gate;
    private String terminal;
    @ManyToOne
    @JoinColumn(name ="plane_id")
    private Plane plane;
    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;
}
