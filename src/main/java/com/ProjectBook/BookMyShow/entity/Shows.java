package com.ProjectBook.BookMyShow.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Shows")

public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @Column(name = "show_time")
    private Date showTime;

    @Column(name = "available_seats")
    private Integer availableSeats;

    @Column(name = "price")
    private Float price;

    public Shows(){

    }

    public Shows(Movie movie, Cinema cinema, Date showTime, Integer availableSeats, Float price) {
        this.movie = movie;
        this.cinema = cinema;
        this.showTime = showTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
