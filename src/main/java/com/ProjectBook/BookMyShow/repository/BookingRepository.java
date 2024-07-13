package com.ProjectBook.BookMyShow.repository;

import com.ProjectBook.BookMyShow.entity.Booking;
import com.ProjectBook.BookMyShow.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByStatusAndBookingTimeBefore(BookingStatus status, LocalDateTime expirytime);
}
