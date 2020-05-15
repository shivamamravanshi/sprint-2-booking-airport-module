package com.capg.flightmanagement.bookingms.exceptions;

public class InvalidBookingDateException extends RuntimeException {
    public InvalidBookingDateException(String msg){
        super(msg);
    }
}
