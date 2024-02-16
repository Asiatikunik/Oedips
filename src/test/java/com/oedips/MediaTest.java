package com.oedips;

import com.oedips.exception.InvalidMediaException;
import com.oedips.model.Movie;
import com.oedips.model.Series;
import com.oedips.service.MediaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MediaTest {
    @InjectMocks
    MediaService mediaService;

    @Test
    public void whenAddInvalidMovie_thenThrowException() {
        Movie invalidMovie = new Movie("Some Movie", List.of("drama"), null, LocalDate.now());
        assertThrows(NullPointerException.class, () -> mediaService.addMedia(invalidMovie));
    }

    @Test
    public void whenAddInvalidSeries_thenThrowException() {
        Series invalidSeries = new Series("Some Series", List.of("drama"), 0);
        assertThrows(NullPointerException.class, () -> mediaService.addMedia(invalidSeries));
    }

}
