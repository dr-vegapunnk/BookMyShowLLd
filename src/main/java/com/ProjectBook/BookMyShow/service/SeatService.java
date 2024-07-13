package com.ProjectBook.BookMyShow.service;

import com.ProjectBook.BookMyShow.entity.ShowSeat;

import java.util.List;

public interface SeatService {
    List<ShowSeat> findAllAvailibleSeats(Long showid);
}
