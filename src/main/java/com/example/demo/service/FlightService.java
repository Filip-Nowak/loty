package com.example.demo.service;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;
    public void addFlight(Flight airport){
        flightRepository.save(airport);
    }
    public Flight getFlightById(long id){
        Optional<Flight> optional= flightRepository.findById(id);
        if(optional.isEmpty())
            return null;
        return optional.get();
    }
    public long getCount(){
        return flightRepository.count();
    }
    public List<Flight> getFlightByPararms(Airport departureAirport){
        return flightRepository.getFlightsWithParams(departureAirport);
    }
}
