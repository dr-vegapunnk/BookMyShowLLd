package com.ProjectBook.BookMyShow.service;

import com.ProjectBook.BookMyShow.entity.Booking;
import com.ProjectBook.BookMyShow.entity.BookingStatus;
import com.ProjectBook.BookMyShow.entity.ShowSeat;
import com.ProjectBook.BookMyShow.repository.BookingRepository;
import com.ProjectBook.BookMyShow.repository.ShowSeatRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowSeatServiceImpl implements ShowSeatService{

    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;

    public ShowSeatServiceImpl(ShowSeatRepository showSeatRepository, BookingRepository bookingRepository) {
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public boolean holdSeat(Long show_id, List<Integer> SeatNumbers) {
        // check if the given seats are avialible or not
        List<ShowSeat> seats = showSeatRepository.findByShowsIdAndSeatNumberIn(show_id,SeatNumbers);

        for(ShowSeat seat : seats ){
            if(seat.getReserved()) return false;
        }

        for(ShowSeat seat : seats){
            seat.setReserved(true);
            seat.setHoldTimeStamp(LocalDateTime.now());
        }
        showSeatRepository.saveAll(seats);

        Booking booking = new Booking();
        booking.setShowId(show_id);
        booking.setSeatNumbers(SeatNumbers);
        booking.setStatus(BookingStatus.PENDING);
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalPrice(seats.size() * seats.get(0).getShow().getPrice());
        bookingRepository.save(booking);

        return true;
    }

    @Override
    public void processPaymentStatus(Long bookingId, boolean isPaymentSuccessful) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null || booking.getStatus() != BookingStatus.PENDING) {
            return;
        }

        if (isPaymentSuccessful) {
            booking.setStatus(BookingStatus.CONFIRMED);
            List<ShowSeat> seats = showSeatRepository.findByShowsIdAndSeatNumberIn(booking.getShowId(), booking.getSeatNumbers());
            for (ShowSeat seat : seats) {
                seat.setReserved(true);
                seat.setHoldTimeStamp(null);
            }
            showSeatRepository.saveAll(seats);
        } else {
            booking.setStatus(BookingStatus.CANCELLED);
            List<ShowSeat> seats = showSeatRepository.findByShowsIdAndSeatNumberIn(booking.getShowId(), booking.getSeatNumbers());
            for (ShowSeat seat : seats) {
                seat.setReserved(false);
                seat.setHoldTimeStamp(null);
            }
            showSeatRepository.saveAll(seats);
        }
        bookingRepository.save(booking);
    }

}


