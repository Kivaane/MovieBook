package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping("/")
    public String home(Model model) {
        List<Movie> featuredMovies = movieService.getAllMovies().stream()
                .limit(4)
                .collect(Collectors.toList());
        model.addAttribute("featuredMovies", featuredMovies);
        model.addAttribute("pageTitle", "CineBook - Home");
        return "index";
    }
}
