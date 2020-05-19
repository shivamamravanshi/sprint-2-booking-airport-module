package com.capg.flightmanagement.bookingms.services;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

import com.capg.flightmanagement.bookingms.dao.*;
import com.capg.flightmanagement.bookingms.entities.Booking;

import com.capg.flightmanagement.bookingms.exceptions.BookingNotFoundException;
import com.capg.flightmanagement.bookingms.exceptions.InvalidArgumentException;
import com.capg.flightmanagement.bookingms.exceptions.InvalidBookingDateException;
import com.capg.flightmanagement.bookingms.exceptions.InvalidBookingIdException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/***
 * @author Shivam Amravanshi
 */
@Service
@Transactional
public class BookingServiceImpl implements IBookingService {

    private IBookingDao dao;

    public IBookingDao getDao() {
        return dao;
    }

    @Autowired
    public void setDao(IBookingDao dao) {
        this.dao = dao;
    }

    /***
     *
     * @param booking
     * throw exception if booking is null
     * else store the booking object in database
     * @return
     */
    @Override
    public Booking addBooking(Booking booking) {
        if (booking == null) {
            throw new InvalidArgumentException("Booking can't be null");
        }
        booking = dao.save(booking);
        return booking;
    }

    /***
     *
     * @param booking
     * throw exception if booking is null
     * else store the booking object in database
     * @return
     */
    @Override
    public Booking modifyBooking(Booking booking) {
        if (booking == null) {
            throw new InvalidArgumentException("Booking can't be null");
        }
        booking = dao.save(booking);
        return booking;
    }

    /***
     *
     * @param bookingId
     * throw exception if bookingId is null
     * else fetch booking object from database
     * @return Booking
     */
    @Override
    public Booking viewBooking(BigInteger bookingId) {
        if (bookingId == null) {
            throw new InvalidBookingIdException("Booking Id can't be null");
        }
        Optional<Booking> optionalBooking = dao.findById(bookingId);
        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        }
        throw new BookingNotFoundException("Booking Not found for Booking Id " + bookingId);
    }

    /***
     *
     * @return List of all booking
     */
    @Override
    public List<Booking> viewAllBooking() {
        List<Booking> bookings = dao.findAll();
        return bookings;
    }

    /***
     * throw exception if bookingId is null
     * else delete the booking if exist in database
     * @param bookingId
     * @return
     */
    @Override
    public Boolean deleteBooking(BigInteger bookingId) {
        if (bookingId == null) {
            throw new InvalidBookingIdException("Booking Id can't be null");
        }
        Optional<Booking> optionalBooking = dao.findById(bookingId);
        if (optionalBooking.isPresent()) {
            dao.deleteById(bookingId);
            return true;
        }
        throw new BookingNotFoundException("Booking Not found for Booking Id +" + bookingId);
    }

    /***
     * Validate Booking
     * @param booking
     */
    @Override
    public void validateBooking(Booking booking) {
        if(booking.getBookingDate().isBefore(LocalDate.now())){
            throw new InvalidBookingDateException("Booking date cannot be past date");
        }

    }


}
