package com.oedips.controller;

import com.oedips.model.SimilarVideoRequest;
import com.oedips.model.Video;
import com.oedips.service.MediaService;
import com.oedips.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private MediaService mediaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addVideo(@RequestBody Video video) {
        Video videoFromService = videoService.addVideo(video);
        return ResponseEntity.created(URI.create("/videos/" + videoFromService.getId())).build();
    }

    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Integer id) {
        return videoService.getVideoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    @GetMapping("/title/{title}")
    public ResponseEntity<List<Video>> getVideoByName(@PathVariable String title) {
        return videoService.getVideoByTitle(title)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Integer id) {
        boolean isDeleted = videoService.deleteVideo(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<Video>> getDeletedVideos() {
        List<Video> deletedVideoIds = videoService.getDeletedVideoIds();
        return ResponseEntity.ok(deletedVideoIds);
    }

    @PostMapping("/similar")
    public ResponseEntity<List<Video>> getSimilarVideos(@RequestBody SimilarVideoRequest request) {
        List<Video> similarVideos = videoService.getSimilarVideos(request.getTitle(), request.getMinLabels());
        if (similarVideos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(similarVideos);
    }



}
