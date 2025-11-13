package com.driveease.driveease_backend.service;


import com.driveease.driveease_backend.model.Booking;
import com.driveease.driveease_backend.model.Car;
import com.driveease.driveease_backend.model.User;
import com.driveease.driveease_backend.model.enums.CarStatus;
import com.driveease.driveease_backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.math.BigDecimal;

@Service
public class BookingService {
    @Autowired private BookingRepository bookingRepository;
    @Autowired
    private CarService carService; // <-- inject it here

    public Booking createBooking(User user, Car car, java.time.LocalDateTime start, java.time.LocalDateTime end) {

        // 1️⃣ Update car status
        car.setStatus(CarStatus.BOOKED); // assuming CarStatus is an enum
        carService.save(car); // persist the change



        Booking b = new Booking();
        b.setUser(user);
        b.setCar(car);
        b.setStartDateTime(start);
        b.setEndDateTime(end);
        long days = Math.max(1, Duration.between(start, end).toDays());
        BigDecimal price = car.getDailyRate().multiply(BigDecimal.valueOf(days));
        b.setTotalPrice(price);
        b.setStatus(com.driveease.driveease_backend.model.enums.BookingStatus.CONFIRMED);
        return bookingRepository.save(b);
    }
}