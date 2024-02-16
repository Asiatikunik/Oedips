package com.oedips.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Movie extends Media {
    private String director;
    private LocalDate releaseDate;

    public Movie(String title, List<String> labels, String director, LocalDate releaseDate) {
        super(title, labels);
        this.director = director;
        this.releaseDate = releaseDate;
    }
}

