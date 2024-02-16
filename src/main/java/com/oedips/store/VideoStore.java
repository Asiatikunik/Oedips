// Dans le fichier src/main/java/com/oedips/store/VideoStore.java
package com.oedips.store;

import com.oedips.model.Video;
import lombok.Getter;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class VideoStore {
    private final ConcurrentHashMap<Integer, Video> hmVideosById = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Video> hmVideosByTitle = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Video> deletedVideos = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        addVideo(new Video("The Matrix", List.of("sci-fi", "dystopia")));
        addVideo(new Video("Inception", List.of("sci-fi", "thriller")));
        addVideo(new Video("Interstellar", List.of("sci-fi", "space")));
        addVideo(new Video("The Dark Knight", List.of("action", "drama")));
        addVideo(new Video("Forrest Gump", List.of("drama", "romance")));
        addVideo(new Video("Pulp Fiction", List.of("crime", "drama")));
        addVideo(new Video("Fight Club", List.of("drama", "action")));
        addVideo(new Video("The Shawshank Redemption", List.of("drama", "crime")));
        addVideo(new Video("The Godfather", List.of("crime", "drama")));
        addVideo(new Video("The Lord of the Rings", List.of("fantasy", "adventure")));

        hmVideosById.forEach((id, video) -> hmVideosByTitle.put(video.getTitle(), video));
    }


    public void addVideo(Video video) {
        Integer newId = getNextId();
        video.setId(newId);
        hmVideosById.put(newId, video);
        hmVideosByTitle.put(video.getTitle(), video);
    }

    private Integer getNextId() {
        return hmVideosById.size() + 1;
    }

    public List<Video> getAllVideosById() {
        return new ArrayList<>(hmVideosById.values());
    }
    public List<Video> getAllVideosByTitle() {
        return new ArrayList<>(hmVideosByTitle.values());
    }

    public Map<String, Video> getVideosByTitle() {
        return hmVideosByTitle;
    }

    public Video getVideoById(Integer id) {
        return hmVideosById.get(id);
    }


    public boolean deleteVideo(Integer id) {
        Video video = hmVideosById.remove(id);
        if (video != null) {
            deletedVideos.put(id, video);
            return true;
        }
        return false;
    }

    public List<Video> getDeletedVideoIds() {
        return new ArrayList<>(deletedVideos.values());
    }

    public List<Video> findSimilarVideos(String title, int minCommonLabels) {
        Video originalVideo = hmVideosByTitle.get(title);
        if (originalVideo == null) {
            return Collections.emptyList();
        }
        List<String> labels = originalVideo.getLabels();

        return hmVideosByTitle.values().stream()
                .filter(video -> !video.getTitle().equals(title)) // exclure la vidéo originale
                .filter(video -> {
                    Set<String> commonLabels = new HashSet<>(video.getLabels());
                    commonLabels.retainAll(labels);
                    return commonLabels.size() >= minCommonLabels;
                })
                .collect(Collectors.toList());
    }


}
