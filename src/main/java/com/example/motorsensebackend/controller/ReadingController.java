package com.example.motorsensebackend.controller;

import com.example.motorsensebackend.model.SensorReading;
import com.example.motorsensebackend.repository.SensorReadingRepository;
import com.example.motorsensebackend.service.LlmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReadingController {

    private final SensorReadingRepository repository;
    private final LlmService llmService;

    public ReadingController(SensorReadingRepository repository, LlmService llmService) {
        this.repository = repository;
        this.llmService = llmService;
    }

    @PostMapping("/readings")
    public ResponseEntity<SensorReading> saveReading(@RequestBody SensorReading reading) {
        SensorReading saved = repository.save(reading);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/readings/latest")
    public ResponseEntity<SensorReading> getLatestReading() {
        SensorReading latest = repository.findTopByOrderByTimestampDesc();
        if (latest == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(latest);
    }

    @GetMapping("/readings")
    public ResponseEntity<List<SensorReading>> getAllReadings() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/readings/check-alert")
    public ResponseEntity<String> checkAlert() {
        SensorReading latest = repository.findTopByOrderByTimestampDesc();
        if (latest == null) return ResponseEntity.ok("No hay datos.");

        if (latest.getTempC() != null && latest.getTempC() > 60) {
            return ResponseEntity.ok("⚠ Temperatura alta detectada.");
        }
        if (latest.getCurrentA() != null && latest.getCurrentA() > 3) {
            return ResponseEntity.ok("⚠ Corriente elevada.");
        }
        return ResponseEntity.ok("Sistema funcionando normalmente.");
    }

    @GetMapping("/analysis/recommendation")
    public ResponseEntity<String> recommendation() {
        try {
            List<SensorReading> all = repository.findAll();
            int from = Math.max(0, all.size() - 20);
            List<SensorReading> last = all.subList(from, all.size());

            String text = llmService.recommend(last);
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error llamando al LLM: " + e.getMessage());
        }
    }
}