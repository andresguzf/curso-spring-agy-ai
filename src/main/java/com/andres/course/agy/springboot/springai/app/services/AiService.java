package com.andres.course.agy.springboot.springai.app.services;

import com.andres.course.agy.springboot.springai.app.dto.CodeDto;
import com.andres.course.agy.springboot.springai.app.dto.Requirement;

public interface AiService {
    String generate(String message);

    String greeting(String name);

    String expert(String message);

    String generateCode(Requirement requirement);

    String explainCode(String code);
}
