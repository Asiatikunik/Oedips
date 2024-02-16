package com.oedips.service;

import com.oedips.model.Media;
import com.oedips.model.Movie;
import com.oedips.model.Series;
import com.oedips.store.MediaStore;
import com.oedips.store.VideoStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MediaService {
    @Autowired
    private MediaStore mediaStore;

    // Just add for testing
    public void addMedia(Media media) {
        if (media instanceof Movie) {
            mediaStore.addMovie((Movie) media);
        } else if (media instanceof Series) {
            mediaStore.addSeries((Series) media);
        }
    }

    public List<Movie> getAllMovies() {
        return mediaStore.getAllMovies();
    }

    public List<Series> getAllSeries() {
        return mediaStore.getAllSeries();
    }
}
