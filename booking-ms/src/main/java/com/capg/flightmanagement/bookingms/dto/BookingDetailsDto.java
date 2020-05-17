package com.capg.flightmanagement.bookingms.dto;

import java.math.BigInteger;
import java.util.List;

/***
 * @author Shivam Amravanshi
 *
 * This class is use to transfer the booking details
 */
public class BookingDetailsDto {
    private BigInteger bookingId;
    //flight Details
    private BigInteger flightNumber;
    private String source;
    private String destination;
    private String  arrivalTime;
    private String  departureTime;
    private String bookingDate;
    private int ticketCost;


    //passengers Detalis
    List<PassengerDetailsDto> passengerList;
    private int numberOfPassengers;
    //userContactDetails
    private BigInteger contactNumber;
    private BigInteger userId;
    private String billingAddress;

    public BigInteger getBookingId() {
        return bookingId;
    }

    public void setBookingId(BigInteger bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(int ticketCost) {
        this.ticketCost = ticketCost;
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

    public String  getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String  arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String  getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public List<PassengerDetailsDto> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<PassengerDetailsDto> passengerList) {
        this.passengerList = passengerList;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
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
}
