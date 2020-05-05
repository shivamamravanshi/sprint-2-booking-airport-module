package com.capg.flightmanagement.bookingms.services;

import java.math.BigInteger;
import java.util.List;
import com.capg.flightmanagement.bookingms.entities.Booking;

public interface IBookingService {
	Booking addBooking(Booking b);

	Booking modifyBooking(Booking b);

	Booking viewBooking(BigInteger userId);

	List<Booking> viewAllBooking();

	Boolean deleteBooking(BigInteger bookingId);

	void validateBooking(Booking b);

}
