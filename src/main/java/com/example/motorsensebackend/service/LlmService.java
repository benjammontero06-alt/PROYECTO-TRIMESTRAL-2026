package com.example.motorsensebackend.service;

import com.example.motorsensebackend.model.SensorReading;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Service
public class LlmService {

    private final RestClient client;
    private final String model;

    public LlmService(
            @Value("${llm.api.key}") String apiKey,
            @Value("${llm.model:openai/gpt-4o-mini}") String model
    ) {
        this.model = model;

        this.client = RestClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("HTTP-Referer", "http://localhost:8080")
                .defaultHeader("X-Title", "MotorSense")
                .build();
    }

    public String recommend(List<SensorReading> lastReadings) {

        StringBuilder input = new StringBuilder();
        input.append("Eres un asistente técnico experto en monitoreo de motores DC.\n");
        input.append("Analiza los datos y responde en español.\n");
        input.append("Devuelve:\n");
        input.append("1. Estado del motor\n");
        input.append("2. Riesgos detectados\n");
        input.append("3. Acciones recomendadas\n\n");

        input.append("Lecturas (más reciente al final):\n");

        for (SensorReading r : lastReadings) {
            input.append(String.format(
                    "- T=%.2fC, I=%.2fA, V=%.2fV, P=%.2fW, PWM=%.2f, time=%s\n",
                    d(r.getTempC()),
                    d(r.getCurrentA()),
                    d(r.getVoltageV()),
                    d(r.getPowerW()),
                    d(r.getPwm()),
                    String.valueOf(r.getTimestamp())
            ));
        }

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("messages", List.of(
                Map.of("role", "user", "content", input.toString())
        ));

        Map<?, ?> resp = client.post()
                .uri("/chat/completions")
                .body(body)
                .retrieve()
                .body(Map.class);

        List<?> choices = (List<?>) resp.get("choices");

        if (choices != null && !choices.isEmpty()) {
            Map<?, ?> choice = (Map<?, ?>) choices.get(0);
            Map<?, ?> message = (Map<?, ?>) choice.get("message");
            return message.get("content").toString();
        }

        return "No se pudo generar recomendación.";
    }

    private double d(Double v) {
        return v == null ? 0.0 : v;
    }
}