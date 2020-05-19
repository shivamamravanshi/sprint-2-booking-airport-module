package com.capg.flightmanagement.bookingms.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/***
 * @author Shivam Amravanshi
 *
 * This class is use to transfer the booking details
 */
public class BookingRequestDto {
    //booking & passenger Detail
    private BigInteger userId;

    private List<PassengerDetailsDto> passengerList;
    private int ticketCost;
    private BigInteger flightNumber;
    private int numberOfPassengers;
    private String billingAddress;

    //source & destination
    private String bookingDate;
    private String source;
    private String destination;
    private String arrivalTime;
    private String departureTime;
    private BigInteger contactNumber;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public BigInteger getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(BigInteger contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String  getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String  bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<PassengerDetailsDto> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<PassengerDetailsDto> passengerList) {
        this.passengerList = passengerList;
    }

    public int getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(int ticketCost) {
        this.ticketCost = ticketCost;
    }

    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
