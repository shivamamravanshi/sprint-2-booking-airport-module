package com.capg.flightmanagement.bookingms.entities;

import javax.persistence.*;
import java.math.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue
    private BigInteger bookingId;
    private BigInteger userId;
    private double ticketCost;
    private int noOfPassenger;
    private BigInteger flightNumber;
    private Date BookingDate;

    @ElementCollection
    private List<BigInteger> passengersUINList;

    public Booking() {

    }

    public Booking(BigInteger bookingId, double ticketCost, int noOfPassenger, BigInteger flightNumber, Date bookingDate,
                   List<BigInteger> passengersUIDList) {
        this.bookingId = bookingId;
        this.ticketCost = ticketCost;
        this.noOfPassenger = noOfPassenger;
        this.flightNumber = flightNumber;
        this.BookingDate = bookingDate;
        this.passengersUINList = passengersUIDList;
    }

    public BigInteger getBookingId() {
        return bookingId;
    }

    public void setBookingId(BigInteger bookingId) {
        this.bookingId = bookingId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    public int getNoOfPassenger() {
        return noOfPassenger;
    }

    public void setNoOfPassenger(int noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }

    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        BookingDate = bookingDate;
    }

    public List<BigInteger> getPassengersUIDList() {
        return passengersUINList;
    }

    public void setPassengersUIDList(List<BigInteger> passengersUIDList) {
        this.passengersUINList = passengersUIDList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return this.bookingId.equals(booking.bookingId);
    }

    @Override
    public int hashCode() {
        return bookingId.hashCode();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", ticketCost=" + ticketCost +
                ", noOfPassenger=" + noOfPassenger +
                ", flightNumber=" + flightNumber +
                ", BookingDate=" + BookingDate +
                ", passengersUIDList=" + passengersUINList +
                '}';
    }
}


