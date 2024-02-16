package com.oedips.controller;

import com.oedips.model.Movie;
import com.oedips.model.Series;
import com.oedips.service.MediaService;
import com.oedips.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;


    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = mediaService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/series")
    public ResponseEntity<List<Series>> getSeries() {
        List<Series> series = mediaService.getAllSeries();
        return ResponseEntity.ok(series);
    }


}
