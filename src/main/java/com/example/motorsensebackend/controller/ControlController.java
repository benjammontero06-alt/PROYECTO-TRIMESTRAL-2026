package com.example.motorsensebackend.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/control")
@CrossOrigin
public class ControlController {

    private boolean autoMode = false;
    private int currentPwm = 180;

    @PostMapping("/mode")
    public String setMode(@RequestParam boolean auto) {
        this.autoMode = auto;
        return auto ? "Modo automático activado" : "Modo manual activado";
    }

    @PostMapping("/pwm")
    public Map<String, Integer> setPwm(@RequestBody Map<String, Integer> body) {
        if (!autoMode && body.containsKey("pwm")) {
            int nuevoPwm = body.get("pwm");

            if (nuevoPwm < 0) nuevoPwm = 0;
            if (nuevoPwm > 255) nuevoPwm = 255;

            currentPwm = nuevoPwm;
        }
        return Map.of("pwm", currentPwm);
    }

    @GetMapping("/pwm")
    public Map<String, Integer> getPwm() {
        return Map.of("pwm", currentPwm);
    }

    public boolean isAutoMode() {
        return autoMode;
    }

    public void setCurrentPwm(int pwm) {
        if (pwm < 0) pwm = 0;
        if (pwm > 255) pwm = 255;
        this.currentPwm = pwm;
    }

    public int getCurrentPwm() {
        return currentPwm;
    }
}