package src.main.java.com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.model.AirportModel;
import com.example.demo.model.FlightModel;
import com.example.demo.service.AirportService;
import com.example.demo.service.FlightService;
import com.example.demo.service.PlaneService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@AllArgsConstructor
public class MainController {
    private UserService userService;
    private FlightService flightService;
    private AirportService airportService;
    private PlaneService planeService;

    @GetMapping("/filldb")
    public String addRandomFlights(Model model) {
        User user = User.builder().id(0).email("mikib@gmail.com").lastname("xd").firstname("miki").nickname("bala").pass("pass").passPlain("passplain").build();
        user.addRole(new Role("user"));
        userService.addUser(user);
        User dbUser = userService.getUserById(1);
        model.addAttribute("user", dbUser);
        return "fill-db";
    }

    @PostMapping("/filldb")
    public String processFillDbForm(Model model, @ModelAttribute("amount") int amountOfFlights) {
        List<Flight> flights = getRandomFlights(amountOfFlights);
        return "success";
    }

    private ArrayList<Flight> getRandomFlights(int amountOfFlights) {
        List<Flight> list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < amountOfFlights; i++) {
            Airport arriveAirport = airportService.getAirportById(random.nextInt((int) airportService.getCount()));
            Airport departureAirport = airportService.getAirportById(random.nextInt((int) airportService.getCount()));
            Plane plane = planeService.getPlaneById(random.nextInt((int) planeService.getCount()));
            LocalDateTime departureDate=getRandomDate();
            LocalDateTime arriveDate=getRandomArriveDate(departureDate);
            flightService.addFlight(Flight.builder().arriveAirport(arriveAirport).departureAirport(departureAirport).arriveDateTime(arriveDate).departureDateTime(departureDate).gate(1).plane(plane).terminal("terminal").tickets(null).build());

        }
        return null;
    }

    private LocalDateTime getRandomArriveDate(LocalDateTime departureDate) {
        Random random=new Random();
        int hours=random.nextInt(5,10);
        return departureDate.plusHours(hours);
    }

    @GetMapping("/searchf")
    private String showSearchFlightsView(Model model, @RequestParam(name = "from") Optional<String> oFrom, @RequestParam(name="departure") Optional<String> oDeparture, @RequestParam(name = "to") Optional<String> oTo, @RequestParam(name = "return") Optional<String> oReturnTime, @RequestParam(name = "passengers") Optional<String> oPassengersCount) {
        String from,to;
        long departure,returnTime;
        int passengers;
        List<Flight>flights=flightService.search(oFrom,oTo,oDeparture,oReturnTime,oPassengersCount);
        List<FlightModel> models=new LinkedList<>();
        for (Flight flight:
             flights) {
            models.add(new FlightModel(new AirportModel(flight.getDepartureAirport().getName()),new AirportModel(flight.getArriveAirport().getName()),flight.getDepartureDateTime(),flight.getArriveDateTime()));
        }
        model.addAttribute("flights",models);
        model.addAttribute("found",models.size()!=0);
        return "search-flights";
    }
    private LocalDateTime getRandomDate(){
        long startEpochDay = LocalDate.now().toEpochDay();
        long endEpochDay = LocalDate.of(2024,6,30).toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        long startSeconds = LocalTime.MIN.getSecond();
        long endSeconds = LocalTime.MAX.getSecond();
        long randomSeconds = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds + 1);

        return randomDate.atTime(LocalTime.ofSecondOfDay(randomSeconds));
    }

}
