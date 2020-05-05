package com.capg.flightmanagement.bookingms.dao;

import java.math.BigInteger;
import com.capg.flightmanagement.bookingms.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingDao extends JpaRepository<Booking,BigInteger> {

}
