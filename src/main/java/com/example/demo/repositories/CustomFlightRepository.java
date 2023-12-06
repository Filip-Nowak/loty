package com.example.demo.repositories;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.entity.User;

import java.util.List;

public interface CustomFlightRepository {
    List<Flight> findUsersByParams(Airport from, Airport to, long departure, long returnTime, int passengers);
}
