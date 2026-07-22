package com.andres.course.agy.springboot.springai.app.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String generate(String message) {
        return this.chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @Override
    public String greeting(String name) {
        return this.chatClient.prompt()
                .system("Responde siempre en aleman y en una 5 líneas, y abajo su traduccion en español")
                .user("Dime hola mundo con mi nombre: " + name)
                .call()
                .content();
    }

    @Override
    public String expert(String message) {
        return this.chatClient.prompt()
                .system("Eres un experto en Java y Spring Boot. Responde de forma clara y simple.")
                .user(message)
                .call()
                .content();
    }
}
