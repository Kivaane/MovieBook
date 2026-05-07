package com.cinema.service;

import com.cinema.model.Movie;
import com.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }
    
    public List<Movie> searchMovies(String keyword) {
        return movieRepository.searchByTitle(keyword);
    }
    
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    
    public boolean updateMovie(Movie movie) {
        return movieRepository.update(movie);
    }
    
    public boolean deleteMovie(Integer id) {
        return movieRepository.deleteById(id);
    }
}
