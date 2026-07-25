package com.andres.course.agy.springboot.springai.app.dto;

import java.util.List;

public record TextAnalysis(
        String summary,
        List<String> keypoint,
        String sentiment
) {
}
