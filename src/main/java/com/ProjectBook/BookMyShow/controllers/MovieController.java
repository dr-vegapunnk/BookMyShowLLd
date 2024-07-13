package com.ProjectBook.BookMyShow.controllers;

import com.ProjectBook.BookMyShow.entity.Cinema;
import com.ProjectBook.BookMyShow.entity.Movie;
import com.ProjectBook.BookMyShow.entity.ShowSeat;
import com.ProjectBook.BookMyShow.entity.Shows;
import com.ProjectBook.BookMyShow.service.MovieService;
import com.ProjectBook.BookMyShow.service.SeatService;
import com.ProjectBook.BookMyShow.service.ShowSeatService;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private ShowSeatService showSeatService;

    @GetMapping("")
    List<Movie> findAll(){
        return movieService.findAll();
    }

    @GetMapping("/{m_id}")
    List<Shows> findShows(@PathVariable Long m_id){
        return movieService.findShows(m_id);
    }

    @GetMapping("/{m_id}/{s_id}")
    List<ShowSeat> findShowSeats(@PathVariable Long s_id){
        return seatService.findAllAvailibleSeats(s_id);
    }

    @PostMapping("/booking/{show_id}")
    public ResponseEntity<String> holdSeats(@PathVariable Long show_id, @RequestBody List<Integer> seatNumbers) {
        boolean result = showSeatService.holdSeat(show_id, seatNumbers);
        if (result) {
            return ResponseEntity.ok("Seats held successfully.");
        } else {
            return ResponseEntity.status(409).body("Failed to hold seats.");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> updatePaymentStatus(@RequestParam Long bookingId, @RequestParam boolean isPaymentSuccessful) {
        showSeatService.processPaymentStatus(bookingId, isPaymentSuccessful);
        return ResponseEntity.ok("Payment status processed.");
    }


}
