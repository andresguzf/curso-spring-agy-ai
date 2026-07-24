# AGENTS.md - Contexto y Guía del Proyecto

Este documento proporciona una visión general técnica, la arquitectura y las pautas para agentes de IA y desarrolladores que trabajen en este proyecto.

## 📌 Descripción del Proyecto
`5-spring-ai` es una aplicación Java construida con **Spring Boot** e integrada con **Spring AI** para interactuar con modelos de lenguaje locales ejecutándose en **Ollama** (específicamente `qwen3:4b`).

---

## 🛠️ Tecnologías y Dependencias
- **Java:** 25
- **Spring Boot:** 4.1.0
- **Spring AI:** 2.0.0 (`spring-ai-starter-model-ollama`)
- **Gestor de dependencias:** Maven (`mvnw`)
- **Modelo LLM Local:** Ollama (`qwen3:4b` en `http://localhost:11434`)

---

## 🎯 Skills Disponibles y Cuándo Gatillarlos

El proyecto cuenta con skills personalizadas ubicadas en `.agents/skills/`:

### 1. `spring-boot-best-practices`
- **Ubicación:** `.agents/skills/spring-boot-best-practices/SKILL.md`
- **Descripción:** Guía para la creación, refactorización y extensión de aplicaciones Spring Boot siguiendo arquitectura en capas limpia, mejores prácticas de desarrollo y estándares de Java moderno.
- **Cuándo se debe gatillar / utilizar:**
  - **Creación de componentes:** Siempre que se solicite crear o modificar una API REST, un controlador (`@RestController`), un servicio (`@Service`), un repositorio (`JpaRepository`), una entidad (`@Entity`), un DTO (`record`) o un mapeador (`Mapper`).
  - **Refactorización de código:** Al desacoplar la lógica de controladores hacia servicios, interfaces e implementaciones.
  - **Inclusión de DTOs:** Cuando se requiera transformar entidades en DTOs para excluir campos sensibles (`password`, auditorías, etc.).
  - **Inicialización de proyectos o módulos:** Al agregar nuevas dependencias o estructuras en capas siguiendo las convenciones del curso.
  - **Vistas con Thymeleaf / UI:** En caso de crear o modificar componentes web monolíticos con Spring Web y Tailwind CSS.

---

## 🏗️ Arquitectura y Estructura del Código

La aplicación sigue una arquitectura en capas limpia (**Controller -> Service Interface -> Service Impl -> Spring AI ChatClient**):

```
src/main/java/com/andres/course/agy/springboot/springai/app/
├── Application.java
├── controllers/
│   └── AiController.java
├── dto/
│   ├── CodeDto.java
│   ├── CodeExplanation.java
│   ├── LineExplanation.java
│   └── Requirement.java
└── services/
    ├── AiService.java
    └── AiServiceImpl.java
```

### Componentes Clave:
1. **`Application.java`**: Clase principal con la anotación `@SpringBootApplication`.
2. **`AiController.java`**: Controlador REST mapeado en `/api/ai`. Inyecta `AiService` y expone los endpoints HTTP.
3. **`Requirement.java`**: DTO (`record`) que encapsula el requerimiento de código (`requirement`).
4. **`CodeDto.java`**: DTO (`record`) estructurado devuelto por la IA que contiene la respuesta formateada (`code`).
5. **`CodeExplanation.java` / `LineExplanation.java`**: DTOs (`record`) mapeados directamente mediante `.entity(CodeExplanation.class)` que estructuran la explicación del código (`language`, `summary`, `lineByLine`, `finalExplanation`).
6. **`AiService.java`**: Interfaz de servicio que define los contratos de negocio:
   - `String generate(String message)`
   - `String greeting(String name)`
   - `String expert(String message)`
   - `String generateCode(Requirement requirement)`
   - `CodeExplanation explainCode(String code)`
7. **`AiServiceImpl.java`**: Implementación anotada con `@Service`. Utiliza `ChatClient` de Spring AI con System Prompts y salidas estructuradas JSON.

---

## ⚙️ Configuración (`application.properties`)

```properties
spring.application.name=5-spring-ai
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.model=qwen3:4b
```

---

## 🌐 Endpoints REST Expuestos

### 1. Generación General de Prompts
- **Ruta:** `GET /api/ai/generate`
- **Parámetros:** `message` (opcional, default: `¿Qué es Spring AI?`)
- **Comportamiento:** Envía una consulta estándar al modelo LLM.

### 2. Saludo Personalizado (System & User Prompts)
- **Ruta:** `GET /api/ai/greeting`
- **Parámetros:** `name` (opcional, default: `Andrés`)
- **Comportamiento:** Usa un System Prompt y un User Prompt con el nombre recibido.

### 3. Experto en Java y Spring Boot (POST)
- **Ruta:** `POST /api/ai/expert`
- **Cuerpo (Body):** Texto plano con la consulta para el experto.
- **Comportamiento:** Usa un System Prompt (*"Eres un experto en Java y Spring Boot. Responde de forma clara y simple."*) y envía el cuerpo de la petición como User Prompt.

### 4. Generación Estructurada de Código (POST)
- **Ruta:** `POST /api/ai/generate-code`
- **Cuerpo (Body):** JSON `Requirement` (`{"requirement": "..."}`)
- **Respuesta:** JSON `CodeDto` (`{"code": "..."}`)
- **Comportamiento:** Emplea un System Prompt de Desarrollador Senior en Java/Spring Boot 4 y responde en formato JSON.

### 5. Explicación Paso a Paso de Código (POST)
- **Ruta:** `POST /api/ai/explain-code`
- **Cuerpo (Body):** Texto plano (`text/plain`) con el código a explicar.
- **Respuesta:** JSON puro estructurado con `language`, `summary`, `lineByLine` (arreglo de líneas y explicación) y `finalExplanation`.
- **Comportamiento:** Usa un System Prompt de profesor experto en programación solicitando una explicación simple, paso a paso y línea por línea en español.

---

## 🚀 Comandos de Construcción y Verificación

- **Compilar el proyecto:**
  ```bash
  ./mvnw clean compile
  ```
- **Ejecutar la aplicación:**
  ```bash
  ./mvnw spring-boot:run
  ```
