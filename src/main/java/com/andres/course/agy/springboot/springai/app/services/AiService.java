package com.andres.course.agy.springboot.springai.app.services;

import com.andres.course.agy.springboot.springai.app.dto.CodeDto;
import com.andres.course.agy.springboot.springai.app.dto.CodeExplanation;
import com.andres.course.agy.springboot.springai.app.dto.Requirement;
import com.andres.course.agy.springboot.springai.app.dto.TextAnalysis;

public interface AiService {
    String generate(String message);

    String greeting(String name);

    String expert(String message);

    String generateCode(Requirement requirement);

    CodeExplanation explainCode(String code);

    String chatFormat(String topic);

    TextAnalysis analyze(String text);
}
