package com.andres.course.agy.springboot.springai.app.services;

import com.andres.course.agy.springboot.springai.app.dto.CodeDto;
import com.andres.course.agy.springboot.springai.app.dto.Requirement;
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

    @Override
    public String generateCode(Requirement requirement) {
        return this.chatClient.prompt()
                .system("""
                        Eres un desarrollador senior, generador de codigo, experto en Java, Jakarta y
                        en Spring Boot 4, con buena práctica. Respondes con codigos completo de
                        preguntas o requerimientos relacionados a Java, JPA,
                        Hibernate y Spring Boot, todo lo relacionado a Java,
                        nada más, ningún otro lenguaje ni contexto,
                        solo programación y código de Java,
                        de lo contrario responde que no soportas esa tecnologia.
                         Responde siempre en español, con buen orden, claro, simple y concreto. Devuelve solo JSON, formato exacto: { "code": "string" }.""")
                .user(requirement.requirement())
                .call().content();
    }
}
