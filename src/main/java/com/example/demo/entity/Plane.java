package src.main.java.com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private int seatsCount;
        private String brand;
        private String model;
}
