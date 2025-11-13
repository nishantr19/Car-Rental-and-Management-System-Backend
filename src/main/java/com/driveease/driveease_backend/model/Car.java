package com.driveease.driveease_backend.model;


import com.driveease.driveease_backend.model.enums.CarStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;

    @Column(unique = true)
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    private CarStatus status = CarStatus.AVAILABLE;
    private BigDecimal dailyRate;

    public Car() {}
    // getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public CarStatus getStatus() { return status; }
    public void setStatus(CarStatus status) { this.status = status; }
    public BigDecimal getDailyRate() { return dailyRate; }
    public void setDailyRate(BigDecimal dailyRate) { this.dailyRate = dailyRate; }
}