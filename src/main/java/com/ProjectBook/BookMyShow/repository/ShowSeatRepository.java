package com.ProjectBook.BookMyShow.repository;

import com.ProjectBook.BookMyShow.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    List<ShowSeat> findByShowsIdAndIsReservedFalse(Long showId);
    List<ShowSeat> findByShowsIdAndSeatNumberIn(Long showId, List<Integer> seatNumbers);
}
