package com.ProjectBook.BookMyShow.service;

import com.ProjectBook.BookMyShow.entity.Cinema;
import com.ProjectBook.BookMyShow.entity.Movie;
import com.ProjectBook.BookMyShow.entity.Shows;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findMoviesByTitle(String title);

    List<Shows> findShows(Long m_id );
}
