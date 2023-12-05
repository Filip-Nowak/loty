package src.main.java.com.example.demo.repositories;

import com.example.demo.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Optional<Airport> getAirportByName(String name);
}
