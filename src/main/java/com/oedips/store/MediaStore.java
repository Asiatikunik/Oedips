package com.oedips.store;

import com.oedips.model.Movie;
import com.oedips.model.Series;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MediaStore {
    private final Map<Integer, Movie> mapMovies = new HashMap<>();
    private final Map<Integer, Series> mapSeries = new HashMap<>();
    @PostConstruct
    public void init() {
        addMovie(new Movie("The Matrix", List.of("sci-fi", "dystopia"), "Lana Wachowski", LocalDate.of(1999, 3, 31)));
        addMovie(new Movie("Inception", List.of("sci-fi", "thriller"), "Christopher Nolan", LocalDate.of(2010, 7, 16)));
        addMovie(new Movie("Interstellar", List.of("sci-fi", "space"), "Christopher Nolan", LocalDate.of(2014, 11, 7)));
        addMovie(new Movie("The Dark Knight", List.of("action", "drama"), "Christopher Nolan", LocalDate.of(2008, 7, 18)));
        addMovie(new Movie("The Godfather", List.of("crime", "drama"), "Francis Ford Coppola", LocalDate.of(1972, 3, 24)));

        addSeries(new Series("Breaking Bad", List.of("chemistry", "drug", "desert", "cancer"), 62));
        addSeries(new Series("Game of Thrones", List.of("fantasy", "power", "dragon"), 73));
        addSeries(new Series("The Witcher", List.of("fantasy", "magic", "monster"), 20));
        addSeries(new Series("Stranger Things", List.of("supernatural", "thriller"), 34));
        addSeries(new Series("Sherlock", List.of("crime", "mystery"), 15));
    }

    public void addMovie(Movie movie) {
        Integer newId = getNextIdMovie();
        movie.setId(newId);
        mapMovies.put(newId, movie);
    }

    public void addSeries(Series series) {
        Integer newId = getNextIdSeries();
        series.setId(newId);
        mapSeries.put(newId, series);
    }

    private Integer getNextIdMovie() {
        return mapMovies.size() + 1;
    }

    private Integer getNextIdSeries() {
        return mapSeries.size() + 1;
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(mapMovies.values());
    }

    public List<Series> getAllSeries() {
        return new ArrayList<>(mapSeries.values());
    }

}
