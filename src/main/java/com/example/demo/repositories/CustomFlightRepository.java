package com.example.demo.repositories;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomFlightRepository {
    List<Flight> findUsersByParams(Airport from, Airport to, LocalDate departure, LocalDate arrive, int passengers);
}
