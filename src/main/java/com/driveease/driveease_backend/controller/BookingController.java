package com.driveease.driveease_backend.controller;


import com.driveease.driveease_backend.model.Booking;
import com.driveease.driveease_backend.model.Car;
import com.driveease.driveease_backend.model.User;
import com.driveease.driveease_backend.repository.UserRepository;
import com.driveease.driveease_backend.service.BookingService;
import com.driveease.driveease_backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired private BookingService bookingService;
    @Autowired private CarService carService;
    @Autowired private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Map<String, String> body, @RequestHeader("Authorization") String auth) {
//        // simple username from token: in JwtFilter we set principal to username
//        String token = auth != null && auth.startsWith("Bearer ") ? auth.substring(7) : null;
//        // For simplicity, extract username by splitting token not ideal; instead call JwtUtil here if needed
//        // But using SecurityContext is easier
//        String username = (String) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }


        var userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body(Map.of("error","unauthenticated"));

        Long carId = Long.valueOf(body.get("carId"));
        LocalDateTime start = LocalDateTime.parse(body.get("start"));
        LocalDateTime end = LocalDateTime.parse(body.get("end"));
        Car car = carService.findById(carId).orElseThrow();

        Booking booking = bookingService.createBooking(userOpt.get(), car, start, end);
        return ResponseEntity.ok(booking);
    }
}