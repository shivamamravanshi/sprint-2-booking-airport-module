package com.capg.flightmanagement.bookingms.exceptions;

public class BookingFullException extends RuntimeException {
    public BookingFullException(String msg){
        super(msg);
    }
}
