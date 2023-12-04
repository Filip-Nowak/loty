package com.example.demo.repositories;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long > {
    @Query("SELECT f from Flight f where f.departureAirport=:departureAirport")
    List<Flight> getFlightsWithParams(@Param("departureAirport")Airport departureAirport);
}
