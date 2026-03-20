package com.example.motorsensebackend.controller;

import com.example.motorsensebackend.model.SensorReading;
import com.example.motorsensebackend.repository.SensorReadingRepository;
import com.example.motorsensebackend.service.LlmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final SensorReadingRepository readingRepository;
    private final LlmService llmService;
    private final ControlController controlController;

    public AnalysisController(SensorReadingRepository readingRepository,
                              LlmService llmService,
                              ControlController controlController) {
        this.readingRepository = readingRepository;
        this.llmService = llmService;
        this.controlController = controlController;
    }

    @GetMapping("/alert")
    public String analyze() {
        List<SensorReading> readings = readingRepository.findTop20ByOrderByTimestampDesc();

        if (readings.isEmpty()) {
            return "No hay datos.";
        }

        String aiResponse = llmService.recommend(readings);

        if (controlController.isAutoMode()) {
            SensorReading latest = readings.get(0);

            double temp = latest.getTempC() != null ? latest.getTempC() : 0.0;
            double current = latest.getCurrentA() != null ? latest.getCurrentA() : 0.0;

            int newPwm;

            if (temp > 60 || current > 3.0) {
                newPwm = 120;
            } else if (current > 2.0) {
                newPwm = 150;
            } else {
                newPwm = 180;
            }

            controlController.setCurrentPwm(newPwm);
        }

        return aiResponse;
    }
}