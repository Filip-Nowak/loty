package com.example.demo.service;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.repositories.WrongDataException;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<Flight> search(Optional<String>  oFrom, Optional<String> oTo, Optional<String> oDeparture,Optional<String> oArrive, Optional<String> oPassengersCount) throws WrongDataException {
        Airport from=null,to=null;
        LocalDate departure=null,arrive=null;
        int passengers;
        if(oFrom.isPresent()){
            if(oFrom.get().length()!=0){
                System.out.println("dupa: "+oFrom.get());
                from=airportService.getAirportByName( oFrom.get());
                if(from==null){
                    System.out.println("wrong from");
                    throw new WrongDataException();
                }

            }
        }
        System.out.println("from:"+from);
        if(oTo.isPresent()){
            if(oTo.get().length()!=0){
                to=airportService.getAirportByName(oTo.get());
                if(to==null){
                    System.out.println("wrong to");
                    throw new WrongDataException();
                }

            }

        }
        if(oDeparture.isPresent()){
            if(oDeparture.get().length()!=0){
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                departure=LocalDate.parse(oDeparture.get(),formatter);
            }
        }
        if(oArrive.isPresent()){
            if(oArrive.get().length()!=0){
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                arrive=LocalDate.parse(oArrive.get(),formatter);
            }
        }
        return flightRepository.findUsersByParams(from,to,departure,arrive,3);


    }

}
