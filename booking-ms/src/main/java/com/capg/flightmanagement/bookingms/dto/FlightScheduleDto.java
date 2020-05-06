package com.capg.flightmanagement.bookingms.dto;

import java.math.BigInteger;

/***
 * @author Shivam Amravanshi
 *
 * This class is use to transfer the Flight Schedule information
 */
public class FlightScheduleDto {
    private BigInteger flightNumber;
    private int availableSeat;
    private String arrivalTime;
    private String  departureTime;

    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String  getArrivalTime() {
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
}
