package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plane {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private int number;
        private int seatsCount;
        private String brand;
        private String model;
}
