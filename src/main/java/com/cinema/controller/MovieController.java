package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.service.MovieService;
import com.cinema.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private ReviewService reviewService;
    
    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies";
    }
    
    @GetMapping("/{id}")
    public String movieDetails(@PathVariable Integer id, Model model) {
        movieService.getMovieById(id).ifPresent(movie -> {
            model.addAttribute("movie", movie);
            model.addAttribute("reviews", reviewService.getReviewsByMovie(id));
        });
        return "movie-details";
    }
    
    @GetMapping("/search")
    public String searchMovies(@RequestParam String q, Model model) {
        model.addAttribute("movies", movieService.searchMovies(q));
        return "movies";
    }
}
