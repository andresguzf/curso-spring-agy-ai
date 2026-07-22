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
Genera un saludo utilizando un System Prompt específico (responde en francés y en una sola línea).

- **Método:** `GET`
- **Parámetro Query:** `name` (opcional)
- **Ejemplo con curl:**
  ```bash
  curl "http://localhost:8080/api/ai/greeting?name=Andres"
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
