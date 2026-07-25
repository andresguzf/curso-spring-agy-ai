package com.andres.course.agy.springboot.springai.app.services;

import com.andres.course.agy.springboot.springai.app.dto.CityInfo;
import com.andres.course.agy.springboot.springai.app.dto.CodeDto;
import com.andres.course.agy.springboot.springai.app.dto.CodeExplanation;
import com.andres.course.agy.springboot.springai.app.dto.Requirement;
import com.andres.course.agy.springboot.springai.app.dto.TextAnalysis;
import com.andres.course.agy.springboot.springai.app.dto.TicketClassification;
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
                             Responde siempre en español, con buen orden, claro, simple y concreto.
                             Devuelve solo JSON, formato exacto:
                             {
                                 "code": "string"
                             }
                        """)
                .user(requirement.requirement())
                .call().content();
    }

    @Override
    public CodeExplanation explainCode(String code) {
        return this.chatClient.prompt()
                .system("""
                        Eres un profesor experto en programación. Explica el código recibido en español, de forma simple, paso a paso y línea por línea.
                        La respuesta debe ser únicamente JSON válido, sin Markdown ni bloques de código, con la siguiente estructura exacta:
                        {
                            "language": "string",
                            "summary": "string",
                            "lineByLine": [
                                {
                                    "line": "línea 1",
                                    "explanation": "explicación de la línea 1"
                                }
                            ],
                            "finalExplanation": "explicación final"
                        }
                        """)
                .user(code)
                .call()
                .entity(CodeExplanation.class);
    }

    @Override
    public String chatFormat(String topic) {
        return this.chatClient.prompt()
                .system("""
                        Eres un experto en tecnología. Responde al tema solicitado de forma clara y bien formateada utilizando los siguientes puntos:
                        - Un título
                        - Seguidos de tres puntos importantes, máximo dos líneas por cada uno
                        - Un ejemplo práctico
                        Maqueta en una estructura HTML, una página con estilo CSS, con Tailwind, theme oscuro, con colores pasteles, sombras, que quede bien bonito como si fuera una página web.
                        Responde solo en español.
                        """)
                .user(topic)
                .call()
                .content();
    }

    @Override
    public TextAnalysis analyze(String text) {
        return this.chatClient.prompt()
                .system("""
                        Eres un experto analista de texto. Analiza el texto recibido y resume el contenido en tres puntos clave en español.
                        La respuesta debe ser únicamente JSON válido, sin Markdown ni bloques de código, con los siguientes campos exactos:
                        {
                            "summary": "resumen del texto en español",
                            "keypoint": [
                                "punto clave 1",
                                "punto clave 2",
                                "punto clave 3"
                            ],
                            "sentiment": "positivo, neutral o negativo"
                        }
                        """)
                .user(text)
                .call()
                .entity(TextAnalysis.class);
    }

    @Override
    public CityInfo cityInfo(String city) {
        return this.chatClient.prompt()
                .system("""
                        Eres un experto informador turístico y geógrafo. Responde únicamente con información verídica y correcta sobre la ciudad consultada.
                        No inventes información. Si no conoces la información, por ejemplo la población o cualquier dato, lo puedes ir a buscar a internet.
                        Si el dato no lo encuentras, déjalo como null.
                        Proporciona los datos en español en formato JSON válido, sin Markdown ni bloques de código, con los siguientes campos exactos:
                        {
                            "city": "nombre de la ciudad",
                            "country": "país al que pertenece",
                            "population": "población estimada o exacta",
                            "description": "descripción de la ciudad"
                        }
                        """)
                .user(city)
                .call()
                .entity(CityInfo.class);
    }

    @Override
    public TicketClassification classifyType(String text) {
        return this.chatClient.prompt()
                .system("""
                        Actúa como un clasificador de tickets de soporte. Clasifica el texto recibido en una de las siguientes tres categorías:
                        - soporte
                        - ventas
                        - reclamos
                        
                        La respuesta debe ser únicamente JSON válido como ticket de soporte, sin Markdown ni bloques de código, con los siguientes campos exactos:
                        {
                            "category": "soporte | ventas | reclamos",
                            "reason": "explicación o razón de la clasificación",
                            "priority": "alta | media | baja"
                        }
                        """)
                .user(text)
                .call()
                .entity(TicketClassification.class);
    }
}
