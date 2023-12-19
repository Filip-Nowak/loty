package com.example.demo.controller;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.model.AirportModel;
import com.example.demo.model.FlightModel;
import com.example.demo.repositories.WrongDataException;
import com.example.demo.service.AirportService;
import com.example.demo.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MyRestController {
    private FlightService flightService;
    private AirportService airportService;
    @GetMapping("/search")
    public ResponseEntity<List<FlightModel>> getFlightModel (@RequestParam(name = "from") Optional<String> oFrom, @RequestParam(name = "departure") Optional<String> oDeparture, @RequestParam(name = "to") Optional<String> oTo, @RequestParam(name = "arrive") Optional<String> oArrive, @RequestParam(name = "passengers") Optional<String> oPassengersCount){
        List<Flight> flights;

        try {
            flights = flightService.search(oFrom, oTo, oDeparture,oArrive, oPassengersCount);
        } catch (WrongDataException e) {


            return new ResponseEntity<List<FlightModel>>(new LinkedList<>(),HttpStatus.NOT_FOUND);
        }
        List<FlightModel> models = new LinkedList<>();
        for (Flight flight :
                flights) {
            models.add(new FlightModel(new AirportModel(flight.getDepartureAirport().getName()), new AirportModel(flight.getArriveAirport().getName()), formatDate(flight.getDepartureDateTime()), formatDate(flight.getArriveDateTime())));
        }
        System.out.println(models);
//        model.addAttribute("wrong", false);
//        model.addAttribute("flights", models);
//        model.addAttribute("found", models.size() != 0);
        return new ResponseEntity<List<FlightModel>>(models,HttpStatus.OK);
    }
    @GetMapping("/test")
    public ResponseEntity<AirportModel> xd(){
        return new ResponseEntity<AirportModel>(new AirportModel("xdd"), HttpStatus.OK);
    }
    private String formatDate(LocalDateTime date) {
        String output = "";
        output += formatLength(date.getDayOfMonth()) + "." + formatLength(date.getMonthValue()) + "." + date.getYear() + " " + formatLength(date.getHour()) + ":" + formatLength(date.getMinute());
        return output;
    }

    private String formatLength(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return Integer.toString(num);
    }
}
