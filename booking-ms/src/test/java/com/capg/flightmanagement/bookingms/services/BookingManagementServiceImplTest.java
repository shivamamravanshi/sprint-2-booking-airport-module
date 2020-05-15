package com.capg.flightmanagement.bookingms.services;

import com.capg.flightmanagement.bookingms.dao.IBookingDao;
import com.capg.flightmanagement.bookingms.dto.PassengerDetailsDto;
import com.capg.flightmanagement.bookingms.entities.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(BookingServiceImpl.class)
public class BookingManagementServiceImplTest {

    @Autowired
    IBookingService bookingService;

    @Autowired
    IBookingDao bookingDao;

    public void testAddBooking_1(){
        Booking booking = new Booking();
        booking.setUserId(new BigInteger("101"));
        booking.setNoOfPassenger(2);
        booking.setTicketCost(12000);
        booking.setFlightNumber(new BigInteger("1111"));
        booking.setBookingDate(LocalDate.of(202,05,30));
        List<BigInteger> passengersUINList = new ArrayList<>();
        BigInteger passenger1UIN = new BigInteger("123412341234");
        passengersUINList.add(passenger1UIN);
        BigInteger passenger2UIN = new BigInteger("123456123456");
        passengersUINList.add(passenger2UIN);
        booking.setPassengersUINList(passengersUINList);

        Booking resultBooking = bookingService.addBooking(booking);

        List<Booking> fetchAllBookings = bookingDao.findAll();
        //Check if one booking is added or not
        Assertions.assertEquals(1,fetchAllBookings.size());
        Booking expectedBooking = fetchAllBookings.get(0);
        //Verify if expectedBooking and booking stored in List are equal
        Assertions.assertEquals(expectedBooking,resultBooking);

        Assertions.assertEquals(booking.getBookingDate(),expectedBooking.getBookingDate());
        Assertions.assertEquals(booking.getFlightNumber(),expectedBooking.getFlightNumber());
        Assertions.assertEquals(booking.getNoOfPassenger(),expectedBooking.getNoOfPassenger());
        Assertions.assertEquals(booking.getPassengersUINList(),expectedBooking.getPassengersUINList());
        Assertions.assertEquals(booking.getTicketCost(),expectedBooking.getTicketCost());
        Assertions.assertEquals(booking.getUserId(),expectedBooking.getUserId());

    }
}
