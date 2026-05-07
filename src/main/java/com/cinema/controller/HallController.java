package com.cinema.controller;

import com.cinema.model.Hall;
import com.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/halls")
public class HallController {
    
    @Autowired
    private HallService hallService;
    
    // ADD THIS METHOD - Main halls page
    @GetMapping
    public String showHallsPage(Model model) {
        List<Hall> halls = hallService.getAllHalls();
        model.addAttribute("halls", halls);
        return "halls";
    }
    
    @GetMapping("/{id}")
    public String showHallDetails(@PathVariable Integer id, Model model) {
        Hall hall = hallService.getHallById(id)
                .orElseThrow(() -> new RuntimeException("Hall not found"));
        model.addAttribute("hall", hall);
        return "hall-details";
    }
}
