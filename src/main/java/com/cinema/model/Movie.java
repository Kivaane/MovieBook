package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * OOP CONCEPT: INHERITANCE
 * Movie extends Media, demonstrating specific implementation of a media item.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Movie extends Media {
    /* ENCAPSULATION: Private fields accessed via Lombok getters/setters */
    private Integer movieId; // Keep this for file ID compatibility
    
    /**
     * OOP CONCEPT: POLYMORPHISM (Method Overriding)
     */
    @Override
    public void displayDetails() {
        System.out.println("Movie: " + title + " - Genre: " + genre);
    }
    
    // ... rest of the fields
    private String title;
    private String genre;
    private Integer duration; // minutes
    private String language;
    private Double price;
    private String showtimes; // "10:00AM|2:00PM|6:00PM"
    private String posterImage;
    private String description;
    private String director;
    private String cast;
    private Double rating; // average from reviews
    
    public String toFileString() {
        return movieId + "," + title + "," + genre + "," + duration + "," + 
               language + "," + price + "," + showtimes + "," + posterImage + "," + 
               description + "," + director + "," + cast + "," + rating;
    }
    
    public static Movie fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            Movie movie = new Movie();
            movie.setMovieId(Integer.parseInt(parts[0]));
            movie.setTitle(parts[1]);
            movie.setGenre(parts[2]);
            movie.setDuration(Integer.parseInt(parts[3]));
            movie.setLanguage(parts[4]);
            movie.setPrice(Double.parseDouble(parts[5]));
            movie.setShowtimes(parts[6]);
            movie.setPosterImage(parts[7]);
            movie.setDescription(parts[8]);
            movie.setDirector(parts[9]);
            movie.setCast(parts[10]);
            movie.setRating(parts.length > 11 ? Double.parseDouble(parts[11]) : 0.0);
            return movie;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String[] getShowtimeArray() {
        return showtimes.split("\\|");
    }
}
