package com.oedips.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Series extends Media {
    private int numberOfEpisodes;

    public Series(String title, List<String> labels, int numberOfEpisodes) {
        super(title, labels);
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
