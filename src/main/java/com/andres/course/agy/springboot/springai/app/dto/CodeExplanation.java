package com.andres.course.agy.springboot.springai.app.dto;

import java.util.List;

public record CodeExplanation(
        String language,
        String summary,
        List<LineExplanation> lineByLine,
        String finalExplanation
) {
}
