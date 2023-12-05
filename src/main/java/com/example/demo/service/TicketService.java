package src.main.java.com.example.demo.service;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Ticket;
import com.example.demo.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    public TicketRepository ticketRepository;
    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }
    public Ticket getTicketById(long id){
        Optional<Ticket> optional= ticketRepository.findById(id);
        if(optional.isEmpty())
            return null;
        return optional.get();
    }
}
