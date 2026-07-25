package com.andres.course.agy.springboot.springai.app.controllers;

import com.andres.course.agy.springboot.springai.app.dto.CityInfo;
import com.andres.course.agy.springboot.springai.app.dto.CodeExplanation;
import com.andres.course.agy.springboot.springai.app.dto.Requirement;
import com.andres.course.agy.springboot.springai.app.dto.TextAnalysis;
import com.andres.course.agy.springboot.springai.app.services.AiService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam(defaultValue = "¿Qué es Spring AI?") String message) {
        return this.aiService.generate(message);
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "Andrés") String name) {
        return this.aiService.greeting(name);
    }

    @PostMapping("/expert")
    public String expert(@RequestBody String message) {
        return this.aiService.expert(message);
    }

    @PostMapping(value = "/generate-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateCode(@RequestBody Requirement requirement) {
        return this.aiService.generateCode(requirement);
    }

    @PostMapping("/explain-code")
    public CodeExplanation explainCode(@RequestBody String code) {
        return this.aiService.explainCode(code);
    }

    @GetMapping(value = "/chat-format", produces = MediaType.TEXT_HTML_VALUE)
    public String chatFormat(@RequestParam(defaultValue = "Spring Boot 4") String topic) {
        return this.aiService.chatFormat(topic);
    }

    @PostMapping("/analyze")
    public TextAnalysis analyze(@RequestBody String text) {
        return this.aiService.analyze(text);
    }

    @GetMapping("/city-info")
    public CityInfo cityInfo(@RequestParam(defaultValue = "Santiago") String city) {
        return this.aiService.cityInfo(city);
    }
}
