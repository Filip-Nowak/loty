package com.example.demo.repositories;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@AllArgsConstructor
@Repository
public class CustomFlightRepositoryImpl implements CustomFlightRepository{
    EntityManager entityManager;
    @Override
    public List<Flight> findUsersByParams(Airport from, Airport to, LocalDate departure, LocalDate arrive, int passengers) {
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
        if(departure!=null){
            if(!start){
                jpqlQuery+="AND day(f.departureDateTime)=day(:departure) AND month(f.departureDateTime)=month(:departure) AND year(f.departureDateTime)=year(:departure)";
            }else{
                start=false;
                jpqlQuery+="WHERE day(f.departureDateTime)=day(:departure) AND month(f.departureDateTime)=month(:departure) AND year(f.departureDateTime)=year(:departure)";

            }
            params.add(new Object[]{"departure",departure});
        }
        if(arrive!=null){
            if(!start){
                jpqlQuery+="AND day(f.arriveDateTime)=day(:arrive) AND month(f.arriveDateTime)=month(:arrive) AND year(f.arriveDateTime)=year(:arrive)";
            }else{
                start=false;
                jpqlQuery+="WHERE day(f.arriveDateTime)=day(:arrive) AND month(f.arriveDateTime)=month(:arrive) AND year(f.arriveDateTime)=year(:arrive)";

            }
            params.add(new Object[]{"arrive",arrive});
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
