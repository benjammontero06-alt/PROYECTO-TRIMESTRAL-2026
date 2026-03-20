package com.example.motorsensebackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tempC;
    private Double currentA;
    private Double voltageV;
    private Double powerW;
    private Double pwm;

    private LocalDateTime timestamp;

    public SensorReading() {}

    public SensorReading(Double tempC, Double currentA, Double voltageV, Double powerW, Double pwm) {
        this.tempC = tempC;
        this.currentA = currentA;
        this.voltageV = voltageV;
        this.powerW = powerW;
        this.pwm = pwm;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters

    public Long getId() { return id; }

    public Double getTempC() { return tempC; }
    public void setTempC(Double tempC) { this.tempC = tempC; }

    public Double getCurrentA() { return currentA; }
    public void setCurrentA(Double currentA) { this.currentA = currentA; }

    public Double getVoltageV() { return voltageV; }
    public void setVoltageV(Double voltageV) { this.voltageV = voltageV; }

    public Double getPowerW() { return powerW; }
    public void setPowerW(Double powerW) { this.powerW = powerW; }

    public Double getPwm() { return pwm; }
    public void setPwm(Double pwm) { this.pwm = pwm; }

    public LocalDateTime getTimestamp() { return timestamp; }
}