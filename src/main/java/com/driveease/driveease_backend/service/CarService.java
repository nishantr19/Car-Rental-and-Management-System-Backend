package com.driveease.driveease_backend.service;


import com.driveease.driveease_backend.model.Car;
import com.driveease.driveease_backend.model.enums.CarStatus;
import com.driveease.driveease_backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired private CarRepository carRepository;

//    public static Car save(Car car) { return carRepository.save(car); }
public Car save(Car car) { return carRepository.save(car); }
    public List<Car> allAvailable() { return carRepository.findByStatus(CarStatus.AVAILABLE); }
    public List<Car> all() { return carRepository.findAll(); }
    public Optional<Car> findById(Long id) { return carRepository.findById(id); }
}