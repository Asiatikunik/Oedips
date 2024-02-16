package com.oedips;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.oedips.model.Video;
import com.oedips.service.VideoService;
import com.oedips.store.VideoStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class VideoServiceTest {

    @Mock
    private VideoStore videoStore;

    @InjectMocks
    private VideoService videoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetVideoById() {
        Video video = new Video();
        video.setId(1);
        video.setTitle("Test Video");

        when(videoStore.getVideoById(1)).thenReturn(video);

        Optional<Video> result = videoService.getVideoById(1);

        assertTrue(result.isPresent());
        assertEquals("Test Video", result.get().getTitle());
    }

    @Test
    public void testGetAllVideos() {
        when(videoStore.getAllVideosById()).thenReturn(List.of(new Video()));

        List<Video> result = videoService.getAllVideos();

        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetVideoByTitle() {
        String title = "Test Video";
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoStore.getVideosByTitle()).thenReturn(Collections.singletonMap(title, video));

        Optional<List<Video>> result = videoService.getVideoByTitle(title);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertEquals(title, result.get().get(0).getTitle());
    }

    @Test
    public void testDeleteVideo() {
        Integer videoId = 1;
        when(videoStore.deleteVideo(videoId)).thenReturn(true);

        boolean result = videoService.deleteVideo(videoId);

        assertTrue(result);
    }

    @Test
    public void testGetDeletedVideoIds() {
        List<Video> deletedIds = new ArrayList<>();
        Video video = new Video();
        video.setId(1);
        video.setTitle("Test Video 1");

        Video video2 = new Video();
        video2.setId(2);
        video2.setTitle("Test Video 2");

        when(videoStore.getDeletedVideoIds()).thenReturn(deletedIds);

        List<Video> result = videoService.getDeletedVideoIds();

        assertEquals(deletedIds, result);
    }

    @Test
    public void testGetSimilarVideos() {
        String title = "Test Video";
        Video testVideo = new Video();
        testVideo.setTitle(title);
        testVideo.setLabels(Arrays.asList("label1", "label2"));

        when(videoStore.findSimilarVideos(title, 2)).thenReturn(List.of(testVideo));

        List<Video> similarVideos = videoService.getSimilarVideos(title, 2);

        assertFalse(similarVideos.isEmpty());
        assertEquals(title, similarVideos.get(0).getTitle());
    }




}
