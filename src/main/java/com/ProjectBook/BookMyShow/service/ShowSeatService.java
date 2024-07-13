package com.ProjectBook.BookMyShow.service;

import java.util.List;

public interface ShowSeatService {
    boolean holdSeat(Long show_id, List<Integer> SeatNumbers);
    void processPaymentStatus(Long bookingId, boolean isPaymentSuccessful);
}
