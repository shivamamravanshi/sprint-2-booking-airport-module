package com.capg.flightmanagement.airportms.exceptions;

public class InvalidAirportCodeException extends RuntimeException {
    public InvalidAirportCodeException(String msg){
        super(msg);
    }
}
