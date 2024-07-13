package com.ProjectBook.BookMyShow.service;

import com.ProjectBook.BookMyShow.entity.ShowSeat;
import com.ProjectBook.BookMyShow.repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatServiceImpl implements SeatService{

    private final ShowSeatRepository showSeatRepository;

    public SeatServiceImpl(ShowSeatRepository showSeatRepository){
        this.showSeatRepository = showSeatRepository;
    }
    @Override
    public List<ShowSeat> findAllAvailibleSeats(Long showid) {
        return showSeatRepository.findByShowsIdAndIsReservedFalse(showid);
    }
}
