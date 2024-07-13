package com.ProjectBook.BookMyShow.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ShowSeat")
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Shows shows;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "is_reserved", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isReserved = false;

    @Column(name = "holdtimestamp")
    private LocalDateTime holdTimeStamp;

    public ShowSeat(){

    }

    public ShowSeat(Shows shows, Integer seatNumber, Boolean isReserved, LocalDateTime holdTimeStamp) {
        this.shows = shows;
        this.seatNumber = seatNumber;
        this.isReserved = isReserved;
        this.holdTimeStamp = holdTimeStamp;
    }

    public LocalDateTime getHoldTimeStamp() {
        return holdTimeStamp;
    }

    public void setHoldTimeStamp(LocalDateTime holdTimeStamp) {
        this.holdTimeStamp = holdTimeStamp;
    }

    public Shows getShow() {
        return shows;
    }

    public void setShow(Shows shows) {
        this.shows = shows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }
}
