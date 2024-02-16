package com.oedips.model;

import lombok.Data;

@Data
public class SimilarVideoRequest {
    private String title;
    private int minLabels;
}

