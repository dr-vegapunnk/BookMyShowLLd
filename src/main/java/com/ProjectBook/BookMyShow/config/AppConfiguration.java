package com.ProjectBook.BookMyShow.config;

import com.ProjectBook.BookMyShow.entity.Booking;
import com.ProjectBook.BookMyShow.entity.BookingStatus;
import com.ProjectBook.BookMyShow.entity.ShowSeat;
import com.ProjectBook.BookMyShow.repository.BookingRepository;
import com.ProjectBook.BookMyShow.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class AppConfiguration {

    private BookingRepository bookingRepository;
    private ShowSeatRepository showSeatRepository;

    public AppConfiguration(BookingRepository bookingRepository, ShowSeatRepository showSeatRepository){
        this.bookingRepository = bookingRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void releaseExpiredHolds() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = now.minusMinutes(5);

        List<Booking> expiredBookings = bookingRepository.findByStatusAndBookingTimeBefore(BookingStatus.PENDING, expiryTime);
        for (Booking booking : expiredBookings) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);

            List<ShowSeat> seats = showSeatRepository.findByShowsIdAndSeatNumberIn(booking.getShowId(), booking.getSeatNumbers());
            for (ShowSeat seat : seats) {
                seat.setReserved(false);
                seat.setHoldTimeStamp(null);
            }
            showSeatRepository.saveAll(seats);
        }
    }
}
