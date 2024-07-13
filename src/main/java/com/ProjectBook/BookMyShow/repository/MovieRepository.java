package com.ProjectBook.BookMyShow.repository;

import com.ProjectBook.BookMyShow.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findByTitle(String title);
}
