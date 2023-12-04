package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.AirportService;
import com.example.demo.service.FlightService;
import com.example.demo.service.PlaneService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Controller
@AllArgsConstructor
public class MainController {
    private UserService userService;
    private FlightService flightService;
    private AirportService airportService;
    private PlaneService planeService;

    @GetMapping("/filldb")
    public String addRandomFlights(Model model){
        User user=User.builder()
                .id(0)
                .email("mikib@gmail.com")
                .lastname("xd")
                .firstname("miki")
                .nickname("bala")
                .pass("pass")
                .passPlain("passplain").build();
        user.addRole(new Role("user"));
        userService.addUser(user);
        User dbUser=userService.getUserById(1);
        model.addAttribute("user",dbUser);
        return "fill-db";
    }
    @PostMapping("/filldb")
    public String processFillDbForm(Model model, @ModelAttribute("amount") int amountOfFlights){
        List<Flight> flights= getRandomFlights(amountOfFlights);
        return "success";
    }
    private ArrayList<Flight> getRandomFlights(int amountOfFlights){
        List<Flight> list=new LinkedList<>();
        Random random=new Random();
        for(int i=0;i<amountOfFlights;i++){
            Airport arriveAirport=airportService.getAirportById(random.nextInt((int)airportService.getCount()));
            Airport departureAirport=airportService.getAirportById(random.nextInt((int)airportService.getCount()));
            Plane plane=planeService.getPlaneById(random.nextInt((int)planeService.getCount()));
            flightService.addFlight(Flight.builder()
                    .arriveAirport(arriveAirport)
                    .departureAirport(departureAirport)
                    .arriveDateTime(0)
                    .departureDateTime(0)
                    .gate(1)
                    .plane(plane)
                    .terminal("terminal")
                    .tickets(null)
                    .build());

        }
        return null;
    }

}
