package src.main.java.com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="arrive")
    private Airport arriveAirport;
    private LocalDateTime arriveDateTime;
    @ManyToOne
    @JoinColumn(name="departure")
    private Airport departureAirport;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureDateTime;
    private int gate;
    private String terminal;
    @ManyToOne
    @JoinColumn(name ="plane_id")
    private Plane plane;
    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;
}
