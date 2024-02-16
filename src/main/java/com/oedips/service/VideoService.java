package com.oedips.service;

import com.oedips.model.Video;
import com.oedips.store.VideoStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private VideoStore videoStore;
    private final AtomicInteger idCounter = new AtomicInteger();

    public Video addVideo(Video video) {
        video.setId(idCounter.incrementAndGet());
        videoStore.addVideo(video);
        return video;
    }

    public List<Video> getAllVideos() {
        return videoStore.getAllVideosById();
    }

    public Optional<Video> getVideoById(Integer id) {
        return Optional.ofNullable(videoStore.getVideoById(id));
    }

    public Optional<List<Video>> getVideoByTitle(String title) {
        List<Video> matchingVideos = new ArrayList<>();
        Map<String, Video> videosByTitle = videoStore.getVideosByTitle();
        String searchTitle = title.toLowerCase(Locale.ROOT);
        for (Map.Entry<String, Video> entry : videosByTitle.entrySet()) {
            if (entry.getKey().toLowerCase(Locale.ROOT).contains(searchTitle)) {
                matchingVideos.add(entry.getValue());
            }
        }
        return matchingVideos.isEmpty() ? Optional.empty() : Optional.of(matchingVideos);
    }

    public boolean deleteVideo(Integer id) {
        return videoStore.deleteVideo(id);
    }

    public List<Video> getDeletedVideoIds() {
        return videoStore.getDeletedVideoIds();
    }

    public List<Video> getSimilarVideos(String title, int minCommonLabels) {
        return videoStore.findSimilarVideos(title, minCommonLabels);
    }


}
