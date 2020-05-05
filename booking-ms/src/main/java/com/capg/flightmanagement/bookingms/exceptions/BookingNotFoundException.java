package com.capg.flightmanagement.bookingms.exceptions;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String msg){
        super(msg);
    }
}
