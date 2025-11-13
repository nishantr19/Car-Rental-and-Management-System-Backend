package com.driveease.driveease_backend.controller;

import com.driveease.driveease_backend.model.Car;
import com.driveease.driveease_backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired private CarService carService;

    @GetMapping("/available")
    public ResponseEntity<List<Car>> available() {
        return ResponseEntity.ok(carService.allAvailable());
    }

    @GetMapping
    public ResponseEntity<List<Car>> all() {
        return ResponseEntity.ok(carService.all());
    }

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
        return ResponseEntity.ok(carService.save(car));
    }
}