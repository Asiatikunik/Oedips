package com.oedips.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public abstract class Media {
    protected Integer id;
    protected String title;
    protected List<String> labels;

    public Media(String title, List<String> labels) {
        this.title = title;
        this.labels = labels;
    }
}

