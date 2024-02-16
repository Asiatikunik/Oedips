package com.oedips;

import static org.junit.jupiter.api.Assertions.*;

import com.oedips.model.Video;
import com.oedips.store.VideoStore;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VideoStoreTest {

    private VideoStore videoStore = new VideoStore();

    @Test
    public void testAddAndGetVideo() {
        Video video = new Video();
        video.setId(1);
        video.setTitle("Test Video");

        videoStore.addVideo(video);

        Video result = videoStore.getVideoById(1);

        assertNotNull(result);
        assertEquals("Test Video", result.getTitle());
    }

    @Test
    public void testGetAllVideosById() {
        Video video1 = new Video();
        video1.setId(1);
        video1.setTitle("Test Video 1");
        Video video2 = new Video();
        video2.setId(2);
        video2.setTitle("Test Video 2");

        videoStore.addVideo(video1);
        videoStore.addVideo(video2);

        List<Video> result = videoStore.getAllVideosById();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllVideosByTitle() {
        Video video1 = new Video();
        video1.setId(1);
        video1.setTitle("Test Video 1");
        Video video2 = new Video();
        video2.setId(2);
        video2.setTitle("Test Video 2");

        videoStore.addVideo(video1);
        videoStore.addVideo(video2);

        List<Video> result = videoStore.getAllVideosByTitle();

        assertEquals(2, result.size());
    }


}
