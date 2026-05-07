package com.cinema.service;

import com.cinema.model.Hall;
import com.cinema.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    
    @Autowired
    private HallRepository hallRepository;
    
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }
    
    public Optional<Hall> getHallById(Integer id) {
        return hallRepository.findById(id);
    }
    
    public Hall saveHall(Hall hall) {
        return hallRepository.save(hall);
    }
    
    public boolean updateHall(Hall hall) {
        return hallRepository.update(hall);
    }
    
    public boolean deleteHall(Integer id) {
        return hallRepository.deleteById(id);
    }
}
