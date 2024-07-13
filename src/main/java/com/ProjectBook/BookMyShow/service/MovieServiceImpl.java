package com.ProjectBook.BookMyShow.service;

import com.ProjectBook.BookMyShow.entity.Movie;
import com.ProjectBook.BookMyShow.entity.Shows;
import com.ProjectBook.BookMyShow.repository.MovieRepository;
import com.ProjectBook.BookMyShow.repository.ShowsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final ShowsRepository showsRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ShowsRepository showsRepository){
        this.movieRepository = movieRepository;
        this.showsRepository = showsRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Shows> findShows(Long m_id) {
        return showsRepository.findByMovieId(m_id);
    }

//    @Override
//    public List<Cinema> findCinema(Long m_id) {
//        return cinemaRepository.findDistinctByMovieId(m_id);
//    }



}
