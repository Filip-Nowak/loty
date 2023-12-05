package src.main.java.com.example.demo.model;

import com.example.demo.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightModel {
    private AirportModel departureAirport;
    private AirportModel arriveAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arriveDateTime;

}
