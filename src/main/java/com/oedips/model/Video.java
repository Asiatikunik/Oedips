package com.oedips.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Video {
    private int id;
    private String title;
    private List<String> labels;

    public Video(String title, List<String> labels) {
        this.title = title;
        this.labels = labels;
    }

}