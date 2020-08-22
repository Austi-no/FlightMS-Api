package com.austine.fms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @Column(name = "BOOKING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "BOOKING_DATE")
    private Date bookingDate;

    @Column(name = "TICKET_PRICE")
    private Double ticketPrice;

    @Column(name = "NO_OF_PASSENGER")
    private Long noOfPassenger;

    @JoinColumn(name = "USER_ID")
    private User userId;

    @JoinColumn(name = "PASSENGER")
    @ManyToOne(fetch = FetchType.EAGER)
    private Passenger passengerList;

    @JoinColumn(name = "FLIGHT")
    private Flight flight;


    public Booking() {
    }

    public Booking(Date bookingDate, Double ticketPrice, Long noOfPassenger, User userId, Passenger passengerList, Flight flight) {
        this.bookingDate = bookingDate;
        this.ticketPrice = ticketPrice;
        this.noOfPassenger = noOfPassenger;
        this.userId = userId;
        this.passengerList = passengerList;
        this.flight = flight;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Long getNoOfPassenger() {
        return noOfPassenger;
    }

    public void setNoOfPassenger(Long noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Passenger getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(Passenger passengerList) {
        this.passengerList = passengerList;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (bookingId != null ? !bookingId.equals(booking.bookingId) : booking.bookingId != null) return false;
        if (bookingDate != null ? !bookingDate.equals(booking.bookingDate) : booking.bookingDate != null) return false;
        if (ticketPrice != null ? !ticketPrice.equals(booking.ticketPrice) : booking.ticketPrice != null) return false;
        if (noOfPassenger != null ? !noOfPassenger.equals(booking.noOfPassenger) : booking.noOfPassenger != null)
            return false;
        if (userId != null ? !userId.equals(booking.userId) : booking.userId != null) return false;
        if (passengerList != null ? !passengerList.equals(booking.passengerList) : booking.passengerList != null)
            return false;
        return flight != null ? flight.equals(booking.flight) : booking.flight == null;
    }

    @Override
    public int hashCode() {
        int result = bookingId != null ? bookingId.hashCode() : 0;
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        result = 31 * result + (noOfPassenger != null ? noOfPassenger.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (passengerList != null ? passengerList.hashCode() : 0);
        result = 31 * result + (flight != null ? flight.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingDate=" + bookingDate +
                ", ticketPrice=" + ticketPrice +
                ", noOfPassenger=" + noOfPassenger +
                ", userId=" + userId +
                ", passengerList=" + passengerList +
                ", flight=" + flight +
                '}';
    }
}
