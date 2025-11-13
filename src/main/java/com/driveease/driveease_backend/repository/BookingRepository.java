package com.driveease.driveease_backend.repository;

import com.driveease.driveease_backend.model.Booking;
import com.driveease.driveease_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}