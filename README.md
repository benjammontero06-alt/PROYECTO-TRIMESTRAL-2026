# 🚀 MotorSense IoT – Proyecto Trimestral 2026

## 📌 Descripción General

**MotorSense IoT** es un sistema inteligente orientado a la Industria 4.0 que permite monitorear, analizar y optimizar el comportamiento de sistemas mecatrónicos mediante el uso de sensores, backend en Java y análisis con inteligencia artificial.

El sistema captura datos desde hardware físico (Arduino), los procesa en un backend desarrollado con Spring Boot y los expone mediante una API REST para su visualización y análisis. Además, integra un modelo de lenguaje (LLM) para generar recomendaciones automáticas basadas en los datos recolectados.

---

## 🎯 Objetivos del Sistema

* Implementar una solución IoT completa (hardware + software)
* Procesar datos en tiempo real desde sensores
* Visualizar métricas relevantes en un dashboard
* Aplicar inteligencia artificial para análisis predictivo
* Simular un entorno de automatización industrial moderno

---

## 🧠 Tecnologías Utilizadas

### 🔹 Backend

* Java + Spring Boot
* API REST (Spring Web)
* Maven
* JSON (Jackson)

### 🔹 Hardware

* Arduino
* Sensores (temperatura, corriente, voltaje, etc.)

### 🔹 Comunicación

* HTTP / Serial (Arduino → Backend)

### 🔹 Frontend

* HTML, CSS, JavaScript
* (Opcional: React / Chart.js)

### 🔹 Inteligencia Artificial

* API LLM (ej: OpenAI / ChatGPT)

---

## 🏗️ Arquitectura del Sistema

```id="archi01"
[Sensores - Arduino]
        ↓
[Comunicación (HTTP / Serial)]
        ↓
[Backend Java - Spring Boot]
        ↓
[Base de Datos]
        ↓
[API REST]
        ↓
[Dashboard Web]
        ↓
[Análisis Inteligente (LLM)]
```

---

## 🔄 Flujo de Datos

1. Arduino captura datos desde sensores
2. Envía los datos al backend mediante HTTP o serial
3. El backend procesa y almacena los datos
4. Los datos se exponen mediante endpoints REST
5. El frontend consume y visualiza la información
6. El backend envía datos al LLM para análisis inteligente
7. Se generan recomendaciones automáticas

---

## 📡 Endpoints Principales

### 📥 Recepción de datos

```http
POST /api/sensors
```

### 📊 Consulta de datos históricos

```http
GET /api/sensors
```

### 🤖 Análisis con IA

```http
POST /api/analyze
```

---

## ⚙️ Funcionalidades

* 📊 Monitoreo en tiempo real
* 📈 Visualización de datos históricos
* ⚠️ Detección de anomalías
* 🤖 Recomendaciones automáticas con IA
* 🔌 Integración hardware–software
* 🌐 API REST funcional

---

## ▶️ Ejecución del Proyecto

### 🔹 Requisitos

* Java 17+
* Maven
* Arduino IDE (para hardware)

---

### 🔹 Backend

En Linux/Mac:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## 📂 Estructura del Proyecto

```
id="struct01"
📁 src/
 ┣ 📁 main/
 ┃ ┣ 📁 java/com/example/motorsensebackend/
 ┃ ┃ ┣ 📁 controller/
 ┃ ┃ ┣ 📁 service/
 ┃ ┃ ┣ 📁 model/
 ┃ ┃ ┗ 📁 repository/
 ┃ ┗ 📁 resources/
 ┃   ┗ application.properties
 ┗ 📁 test/

📁 ARDUINO/
📄 pom.xml
📄 README.md
```

---

## 🤖 Integración con Inteligencia Artificial

El sistema utiliza un modelo de lenguaje (LLM) para:

* Analizar patrones de comportamiento
* Detectar posibles fallos
* Generar recomendaciones de mantenimiento
* Optimizar el rendimiento del sistema

Ejemplo de uso:

```id="llm01"
"El sistema detecta un aumento de temperatura constante, se recomienda revisar el motor o sistema de ventilación."
```

---

## Pruebas del Sistema

Puedes probar los endpoints usando:

* Postman
* IntelliJ HTTP Client (`test.http`)

---

## Posibles Mejoras

* Implementación de MQTT
* Dashboard con gráficos en tiempo real
* Alertas automáticas (email o notificaciones)
* Machine Learning más avanzado

---

## Autor

* Bryan Ramírez

---

## Estado del Proyecto

En desarrollo avanzado

---

## 🏆 Aprendizajes Clave

* Desarrollo de sistemas IoT reales
* Integración de hardware con backend
* Diseño de APIs REST
* Uso de inteligencia artificial aplicada
* Arquitectura de sistemas distribuidos

---

##

---
