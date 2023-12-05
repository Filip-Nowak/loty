package src.main.java.com.example.demo.controller;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.service.AirportService;
import com.example.demo.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class MyRestController {
    private FlightService flightService;
    private AirportService airportService;
    @GetMapping("/search")
    public List<Flight> getFlight(@RequestParam Map<String,String> params){
        Airport destinyAirport=airportService.getAirportById(1);
        System.out.println(destinyAirport.getName());
        List<Flight> flights=flightService.getFlightByParams(destinyAirport);
        System.out.println(flights);
        return flights;
    }
}
