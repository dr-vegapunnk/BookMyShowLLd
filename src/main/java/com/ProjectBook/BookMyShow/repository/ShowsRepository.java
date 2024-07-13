package com.ProjectBook.BookMyShow.repository;

import com.ProjectBook.BookMyShow.entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowsRepository extends JpaRepository<Shows, Long> {
    List<Shows> findByMovieId(Long movie_id);
}
