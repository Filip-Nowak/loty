package com.example.demo;

import com.example.demo.constants.Constants;
import com.example.demo.entity.Airport;
import com.example.demo.entity.Plane;
import com.example.demo.service.AirportService;
import com.example.demo.service.PlaneService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class Demo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(AirportService airportService, PlaneService planeService) {
        return runner -> {
            loadAirports(airportService);
            loadPlanes(planeService);
        };
    }

    private void loadPlanes(PlaneService planeService) {
        Random random=new Random();
        for(int i=0;i<100;i++){
            Plane plane=Plane.builder()
                    .brand(Constants.BRANDS[random.nextInt(Constants.BRANDS.length)])
                    .model(Constants.MODELS[random.nextInt(Constants.MODELS.length)])
                    .seatsCount(random.nextInt(100,500)).build();
            planeService.addPlane(plane);
        }
    }

    private void loadAirports(AirportService airportService) {
        for (int i = 0; i < Constants.CITIES.length; i++) {
            airportService.addAirport(Airport.
                    builder()
                    .name(Constants.CITIES[i]).build());
        }
    }
}
