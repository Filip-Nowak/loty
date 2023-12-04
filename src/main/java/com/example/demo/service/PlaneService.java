package com.example.demo.service;

import com.example.demo.entity.Airport;
import com.example.demo.entity.Plane;
import com.example.demo.repositories.PlaneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaneService {
    private PlaneRepository planeRepository;
    public void addPlane(Plane airport){
        planeRepository.save(airport);
    }
    public Plane getPlaneById(long id){
        Optional<Plane> optional= planeRepository.findById(id);
        if(optional.isEmpty())
            return null;
        return optional.get();
    }
    public long getCount(){
        return planeRepository.count();
    }
}
