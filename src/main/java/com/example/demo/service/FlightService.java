package com.example.demo.service;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;
    private AirportService airportService;
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

    public List<Flight> getFlightByParams(Airport departureAirport){
        return flightRepository.getFlightsWithParams(departureAirport);
    }

    public List<Flight> search(Optional<String>  oFrom, Optional<String> oTo, Optional<String> oDeparture, Optional<String> oReturnTime, Optional<String> oPassengersCount) throws Exception {
        Airport from=null,to=null;
        long departure=0,returnTime=0;
        int passengers;
        if(oFrom.isPresent()){
            from=airportService.getAirportByName( oFrom.get());
            if(from==null)
                throw new Exception("wrong data");

        }
        if(oTo.isPresent()){
            to=airportService.getAirportByName(oTo.get());
            if(to==null)
                throw new Exception("wrong data");
        }
        return flightRepository.findUsersByParams(from,to,3,3,3);


    }
}
