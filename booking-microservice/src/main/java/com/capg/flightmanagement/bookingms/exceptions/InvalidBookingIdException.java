package com.capg.flightmanagement.bookingms.exceptions;

public class InvalidBookingIdException extends RuntimeException{
    public InvalidBookingIdException(String msg){
        super(msg);
    }
}
