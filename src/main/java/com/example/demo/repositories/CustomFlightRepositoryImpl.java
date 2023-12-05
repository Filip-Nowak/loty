package src.main.java.com.example.demo.repositories;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@AllArgsConstructor
@Repository
public class CustomFlightRepositoryImpl implements CustomFlightRepository{
    EntityManager entityManager;
    @Override
    public List<Flight> findUsersByParams(Airport from, Airport to, long departure, long returnTime, int passengers) {
        String jpqlQuery = "SELECT f FROM Flight f ";
        boolean start=true;
        LinkedList<Object[]> params=new LinkedList<>();
        if(from!=null){
            start=false;
            jpqlQuery+="WHERE f.departureAirport=:fromParam ";
            params.add(new Object[]{"fromParam",from});
        }
        if(to!=null){
            if(!start){
                jpqlQuery+="AND f.arriveAirport=:toParam ";
            }
            else{
                start=false;
                jpqlQuery+="WHERE f.arriveAirport=:toParam ";

            }
            params.add(new Object[]{"toParam",to});

        }

        Query query = entityManager.createQuery(jpqlQuery);
        for (Object[] o :
                params) {
            query.setParameter((String) o[0],o[1]);
        }

// Pobierz tekstową reprezentację zapytania
        String queryString = query.unwrap(org.hibernate.query.Query.class).getQueryString();

        System.out.println("Tekstowe zapytanie JPQL: " + queryString);
        return query.getResultList();
    }

}
