package com.driveease.driveease_backend.repository;


import com.driveease.driveease_backend.model.Car;
import com.driveease.driveease_backend.model.enums.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatus(CarStatus status);
}