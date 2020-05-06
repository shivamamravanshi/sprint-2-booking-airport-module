package com.capg.flightmanagement.bookingms.entities;

import javax.persistence.*;
import java.math.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private LocalDate BookingDate;

    @ElementCollection
    private List<BigInteger> passengersUINList;

    /***
     * default non parametrized constructor
     */
    public Booking() {

    }

    /***
     *
     * @param bookingId     Initialize bookingId
     * @param ticketCost    Initialize ticketCost
     * @param noOfPassenger Initialize noOfPassenger
     * @param flightNumber  Initialize flightNumber
     * @param bookingDate   Initialize bookingDate
     * @param passengersUINList Initialize passengerUINList
     */
    public Booking(BigInteger bookingId, double ticketCost, int noOfPassenger, BigInteger flightNumber, LocalDate bookingDate,
                   List<BigInteger> passengersUINList) {
        this.bookingId = bookingId;
        this.ticketCost = ticketCost;
        this.noOfPassenger = noOfPassenger;
        this.flightNumber = flightNumber;
        this.BookingDate = bookingDate;
        this.passengersUINList = passengersUINList;
    }

    /***
     *
     * @return bookingId
     */
    public BigInteger getBookingId() {
        return bookingId;
    }

    /***
     * set Booking Id
     * @param bookingId
     */
    public void setBookingId(BigInteger bookingId) {
        this.bookingId = bookingId;
    }

    /***
     *
     * @return userId as BigInteger
     */
    public BigInteger getUserId() {
        return userId;
    }

    /***
     * setUserId
     * @param userId
     */
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    /***
     *
     * @return ticketCost
     */
    public double getTicketCost() {
        return ticketCost;
    }

    /***
     * set Ticket Cost
     * @param ticketCost
     */
    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    /***
     *
     * @return noOfPassenger
     */
    public int getNoOfPassenger() {
        return noOfPassenger;
    }

    /***
     * set NumberOfPassenger
     * @param noOfPassenger
     */
    public void setNoOfPassenger(int noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }

    /***
     *
     * @return  flightNumber
     */
    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    /***
     * set flightNumber
     * @param flightNumber
     */
    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }

    /***
     *
     * @return bookingDate
     */
    public LocalDate getBookingDate() {
        return BookingDate;
    }

    /***
     * set Booking Date
     * @param bookingDate
     */
    public void setBookingDate(LocalDate bookingDate) {
        BookingDate = bookingDate;
    }

    /***
     *
     * @return List of passengers unique Identification number
     */
    public List<BigInteger> getPassengersUINList() {
        return passengersUINList;
    }

    /***
     * set List of passengers unique Identification Number
     * @param passengersUIDList
     */
    public void setPassengersUINList(List<BigInteger> passengersUIDList) {
        this.passengersUINList = passengersUIDList;
    }

    /***
     * check the equality of Booking object
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof Booking)) return false;
        Booking booking = (Booking) object;
        return this.bookingId.equals(booking.bookingId);
    }

    /***
     * override hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return bookingId.hashCode();
    }

    /***
     *
     * @return  Combine Booking Details
     */
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


