package com.example.motorsensebackend.repository;

import com.example.motorsensebackend.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findTop20ByOrderByTimestampDesc();

    SensorReading findTopByOrderByTimestampDesc();
}