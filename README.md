# 5-spring-ai 🚀

Aplicación Spring Boot para la integración con inteligencia artificial utilizando **Spring AI** y **Ollama**.

## 📋 Requisitos Previos

- **Java 25** instalado.
- **Ollama** corriendo localmente en `http://localhost:11434`.
- Modelo `qwen3:4b` descargado en Ollama. Puedes descargarlo ejecutando:
  ```bash
  ollama pull qwen3:4b
  ```

---

## 🛠️ Tecnologías

- **Java 25**
- **Spring Boot 4.1.0**
- **Spring AI 2.0.0** (`spring-ai-starter-model-ollama`)
- **Maven**

---

## 🏗️ Estructura del Proyecto

El proyecto implementa una arquitectura desacoplada por capas:

- **Controlador:** [AiController.java](file:///Users/andres/Desktop/SpringAntigravityAI/5-spring-ai/src/main/java/com/andres/course/agy/springboot/springai/app/controllers/AiController.java) - Expone los endpoints REST `/api/ai`.
- **DTOs (Java Records):** [Requirement.java](file:///Users/andres/Desktop/SpringAntigravityAI/5-spring-ai/src/main/java/com/andres/course/agy/springboot/springai/app/dto/Requirement.java), [CodeDto.java](file:///Users/andres/Desktop/SpringAntigravityAI/5-spring-ai/src/main/java/com/andres/course/agy/springboot/springai/app/dto/CodeDto.java), [CodeExplanation.java](file:///Users/andres/Desktop/SpringAntigravityAI/5-spring-ai/src/main/java/com/andres/course/agy/springboot/springai/app/dto/CodeExplanation.java), [LineExplanation.java](file:///Users/andres/Desktop/SpringAntigravityAI/5-spring-ai/src/main/java/com/andres/course/agy/springboot/springai/app/dto/LineExplanation.java) - Representación de datos de entrada/salida.
- **Interfaz de Servicio:** [AiService.java](file:///Users/andres/Desktop/SpringAntigravityAI/5-spring-ai/src/main/java/com/andres/course/agy/springboot/springai/app/services/AiService.java) - Define las operaciones disponibles.
- **Implementación del Servicio:** [AiServiceImpl.java](file:///Users/andres/Desktop/SpringAntigravityAI/5-spring-ai/src/main/java/com/andres/course/agy/springboot/springai/app/services/AiServiceImpl.java) - Realiza la comunicación con Ollama mediante `ChatClient`.

---

## 🔌 Endpoints de la API

### 1. Generar Respuesta (`/api/ai/generate`)
Permite realizar consultas directas al modelo de IA.

- **Método:** `GET`
- **Parámetro Query:** `message` (opcional)
- **Ejemplo con curl:**
  ```bash
  curl "http://localhost:8080/api/ai/generate?message=Explica+que+es+Spring+Boot"
  ```

### 2. Saludo Personalizado (`/api/ai/greeting`)
Genera un saludo utilizando un System Prompt específico.

- **Método:** `GET`
- **Parámetro Query:** `name` (opcional)
- **Ejemplo con curl:**
  ```bash
  curl "http://localhost:8080/api/ai/greeting?name=Andres"
  ```

### 3. Consulta a Experto en Java y Spring Boot (`/api/ai/expert`)
Recibe una consulta por POST en formato texto y responde utilizando el rol de un experto en Java y Spring Boot.

- **Método:** `POST`
- **Cuerpo (Body):** Texto plano (`text/plain`)
- **Ejemplo con curl:**
  ```bash
  curl -X POST "http://localhost:8080/api/ai/expert" \
    -H "Content-Type: text/plain" \
    -d "¿Qué es la inyección de dependencias en Spring Boot?"
  ```

### 4. Generación Estructurada de Código (`/api/ai/generate-code`)
Recibe un requerimiento en JSON y devuelve un objeto JSON estructurado `CodeDto` con el código generado por la IA.

- **Método:** `POST`
- **Cuerpo (Body):** JSON (`application/json`)
- **Ejemplo con curl:**
  ```bash
  curl -X POST "http://localhost:8080/api/ai/generate-code" \
    -H "Content-Type: application/json" \
    -d '{"requirement": "Crea una clase Singleton en Java 25"}'
  ```

### 5. Explicación Paso a Paso de Código (`/api/ai/explain-code`)
Recibe código por POST y devuelve una explicación en JSON paso a paso y línea por línea realizada por un profesor experto en programación.

- **Método:** `POST`
- **Cuerpo (Body):** Texto plano (`text/plain`)
- **Ejemplo con curl:**
  ```bash
  curl -X POST "http://localhost:8080/api/ai/explain-code" \
    -H "Content-Type: text/plain" \
    -d "public class Calculator { public int add(int a, int b) { return a + b; } }"
  ```

---

## 🚀 Ejecución del Proyecto

1. **Asegúrate de que Ollama esté iniciado:**
   ```bash
   ollama serve
   ```

2. **Compilar el proyecto:**
   ```bash
   ./mvnw clean compile
   ```

3. **Iniciar la aplicación:**
   ```bash
   ./mvnw spring-boot:run
   ```
