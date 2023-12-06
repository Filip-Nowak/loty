package com.example.demo.service;

import com.example.demo.entity.Airport;
import com.example.demo.entity.User;
import com.example.demo.repositories.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportService {
    private AirportRepository airportRepository;
    public void addAirport(Airport airport){
        airportRepository.save(airport);
    }
    public Airport getAirportById(long id){
        Optional<Airport> optional= airportRepository.findById(id);
        if(optional.isEmpty())
            return null;
        return optional.get();
    }
    public long getCount(){
        return airportRepository.count();
    }
    public Airport getAirportByName(String name){
        Optional<Airport> optional= airportRepository.getAirportByName(name);
        if(optional.isEmpty())
            return null;
        return optional.get();
    }
}
