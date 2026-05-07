package com.cinema.repository;

import com.cinema.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "movies.txt";
    
    public Movie save(Movie movie) {
        if (movie.getMovieId() == null) {
            movie.setMovieId(fileHandler.getNextId(FILENAME));
        }
        fileHandler.writeToFile(FILENAME, movie.toFileString());
        return movie;
    }
    
    public Optional<Movie> findById(Integer id) {
        String line = fileHandler.findById(FILENAME, id);
        return line != null ? Optional.ofNullable(Movie.fromFileString(line)) : Optional.empty();
    }
    
    public List<Movie> findAll() {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Movie::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    public List<Movie> searchByTitle(String keyword) {
        return fileHandler.searchByField(FILENAME, keyword, 1).stream()
                .map(Movie::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    public boolean update(Movie movie) {
        int lineNumber = fileHandler.getLineNumberById(FILENAME, movie.getMovieId());
        if (lineNumber >= 0) {
            return fileHandler.updateLine(FILENAME, lineNumber, movie.toFileString());
        }
        return false;
    }
    
    public boolean deleteById(Integer id) {
        int lineNumber = fileHandler.getLineNumberById(FILENAME, id);
        return lineNumber >= 0 && fileHandler.deleteLine(FILENAME, lineNumber);
    }
}
