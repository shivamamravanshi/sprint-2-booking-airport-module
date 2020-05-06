package com.capg.flightmanagement.bookingms.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class BookingDetailsDto {
    //flight Details
    private BigInteger flightNumber;
    private String source;
    private String destination;
    private String  arrivalTime;
    private String  departureTime;

    //passengers Detalis
    List<PassengerDetailsDto> passengerList;

    //userContactDetails
    private BigInteger userPhone;
    private BigInteger userId;
    private String billingAddress;

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

    public BigInteger getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(BigInteger userPhone) {
        this.userPhone = userPhone;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
