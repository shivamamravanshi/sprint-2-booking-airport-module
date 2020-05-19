package com.capg.flightmanagement.bookingms.controller;

import com.capg.flightmanagement.bookingms.dto.*;
import com.capg.flightmanagement.bookingms.entities.Booking;
import com.capg.flightmanagement.bookingms.exceptions.BookingFullException;
import com.capg.flightmanagement.bookingms.exceptions.BookingNotFoundException;
import com.capg.flightmanagement.bookingms.exceptions.InvalidBookingIdException;
import com.capg.flightmanagement.bookingms.services.IBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
/***
 * @author Shivam Amravanshi
 */
public class BookingRestController {
    @Autowired
    private IBookingService bookingService;

    private static final Logger Log = LoggerFactory.getLogger(BookingRestController.class);

    @Autowired
    RestTemplate restTemplate;
/*
    @Value("${userService.baseUrl}")
    private String userServiceBaseUrl;

    @Value("${flightScheduleService.baseUrl")
    private String flightScheduleServiceBaseUrl;

    @Value("${passengerService.baseUrl")
    private String passengerServiceBaseUrl;
*/

    @Value("${airportService.baseUrl}")
    private String airportServiceBaseUrl;

    /***
     * create Booking if new req
     * @param bookingRequestDto
     * @return
     */
    @PostMapping("/new")
    public ResponseEntity<BookingDetailsDto> createBookingRequest(@RequestBody @Valid BookingRequestDto bookingRequestDto) {
        BookingDetailsDto bookingDetailsDto = convertToResponseDto(bookingRequestDto);
        Booking booking = new Booking();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate bookingDate = LocalDate.parse(bookingRequestDto.getBookingDate(), formatter);

        booking.setBookingDate(bookingDate);
        booking.setFlightNumber(bookingRequestDto.getFlightNumber());
        booking.setTicketCost(bookingRequestDto.getTicketCost());
        booking.setUserId(bookingRequestDto.getUserId());

        List<BigInteger> passengerUINList = passengerUINList(bookingRequestDto);

        booking.setPassengersUINList(passengerUINList);
        booking.setNoOfPassenger(bookingRequestDto.getNumberOfPassengers());
        bookingService.validateBooking(booking);
        booking = bookingService.addBooking(booking);
        bookingDetailsDto.setBookingId(booking.getBookingId());
        acknowledgeBooking(booking);
        requestPassengerStore(bookingDetailsDto.getPassengerList());

        ResponseEntity<BookingDetailsDto> response = new ResponseEntity<>(bookingDetailsDto, HttpStatus.OK);
        return response;
    }

    /***
     * this method will send the acknowledge to flightSchedule along with booking object
     * @param booking
     */
    private void acknowledgeBooking(Booking booking) {
/*
        String url = flightScheduleServiceBaseUrl+"/booked";
        restTemplate.put(url,booking);
*/
    }

    /***
     * this method will send the list of passengers to Passenger service to store passengers Details to database
     * @param passengerDetailsDtoList
     */
    private void requestPassengerStore(List<PassengerDetailsDto> passengerDetailsDtoList) {
/*
        String url = passengerServiceBaseUrl+"/store";
        restTemplate.put(url,passengerDetailsDtoList);
*/
    }

    /***
     *
     * @param bookingRequestDto
     * @return the list of passengers unique Identification Number
     */
    private List<BigInteger> passengerUINList(BookingRequestDto bookingRequestDto) {
        List<BigInteger> passengerUINList = new ArrayList<>();
        List<PassengerDetailsDto> passengerList = bookingRequestDto.getPassengerList();
        for (PassengerDetailsDto passenger : passengerList) {
            passengerUINList.add(passenger.getPassengerUIN());
        }
        return passengerUINList;
    }

    /***
     * convert the booking request to booking details
     * @param bookingRequestDto
     * @return
     */
    private BookingDetailsDto convertToResponseDto(BookingRequestDto bookingRequestDto) {
        BookingDetailsDto bookingDetailsDto = new BookingDetailsDto();

        List<PassengerDetailsDto> responsePassengerList = convertToResponsePassengerList(bookingRequestDto.getPassengerList());
        bookingDetailsDto.setPassengerList(responsePassengerList);

        bookingDetailsDto.setBookingDate(bookingRequestDto.getBookingDate());
        bookingDetailsDto.setFlightNumber(bookingRequestDto.getFlightNumber());
        bookingDetailsDto.setSource(bookingRequestDto.getSource());
        bookingDetailsDto.setTicketCost(bookingRequestDto.getTicketCost());
        bookingDetailsDto.setContactNumber(bookingRequestDto.getContactNumber());
        bookingDetailsDto.setDestination(bookingRequestDto.getDestination());
        bookingDetailsDto.setBillingAddress(bookingRequestDto.getBillingAddress());
        bookingDetailsDto.setNumberOfPassengers(bookingRequestDto.getNumberOfPassengers());
        //schedule
        FlightScheduleDto flightScheduleDto = getScheduleFlightDetails(bookingRequestDto.getFlightNumber());

        if (bookingRequestDto.getPassengerList().size() > flightScheduleDto.getAvailableSeat()) {
            throw new BookingFullException("Booking is full for flight Number " + bookingRequestDto.getFlightNumber() + "for Date " + bookingRequestDto.getBookingDate());
        }

        bookingDetailsDto.setArrivalTime(bookingRequestDto.getArrivalTime());
        bookingDetailsDto.setDepartureTime(bookingRequestDto.getDepartureTime());

        bookingDetailsDto.setUserId(bookingRequestDto.getUserId());
        bookingDetailsDto.setContactNumber(bookingRequestDto.getContactNumber());

        return bookingDetailsDto;
    }

    /***
     * return the list of passenger along with autogenerated PNR Number
     * @param request
     * @return
     */
    private List<PassengerDetailsDto> convertToResponsePassengerList(List<PassengerDetailsDto> request) {
        List<PassengerDetailsDto> responsePassengerList = new ArrayList<>();
        for (PassengerDetailsDto passenger : request) {
            PassengerDetailsDto addPassengerToResponse = new PassengerDetailsDto();
            addPassengerToResponse.setPassengerUIN(passenger.getPassengerUIN());
            addPassengerToResponse.setPassengerName(passenger.getPassengerName());
            addPassengerToResponse.setPassengerAge(passenger.getPassengerAge());
            addPassengerToResponse.setGender(passenger.getGender());
            addPassengerToResponse.setLuggage(passenger.getLuggage());
            responsePassengerList.add(addPassengerToResponse);
        }
        return responsePassengerList;
    }

    /***
     * fetch the schedule information by flightNumber
     * @param flightNumber
     * @return
     */
    private FlightScheduleDto getScheduleFlightDetails(BigInteger flightNumber) {
/*
        //Integrated
        String url = flightScheduleServiceBaseUrl +"/get/"+flightNumber;
        FlightScheduleDto  flightScheduleDto = restTemplate.getForObject(url,FlightScheduleDto.class);
        return flightScheduleDto;
*/
        //Isolation
        FlightScheduleDto flightScheduleDto = new FlightScheduleDto();
        flightScheduleDto.setAvailableSeat(10);
        flightScheduleDto.setFlightNumber(new BigInteger("1111"));
        flightScheduleDto.setArrivalTime("20:30");
        flightScheduleDto.setDepartureTime("21:30");
        //flightScheduleDto.setArrivalTime(LocalTime.of(20,30));
        //flightScheduleDto.setDepartureTime(LocalTime.of(21,00));
        return flightScheduleDto;
    }

    @GetMapping("/flightsearch/{source}/{destination}/{date}")
    public ResponseEntity<FlightAndScheduleInfoDto[]> fetchSearchedFlights(@PathVariable("source") String src,
                                                                           @PathVariable("destination") String destination, @PathVariable("date") String date) {
        FlightAndScheduleInfoDto[] flights = fetchFlights(src, destination, date);
        ResponseEntity<FlightAndScheduleInfoDto[]> response = new ResponseEntity<>(flights, HttpStatus.OK);
        return response;
    }

    /***
     * return dummy data of flight schedule
     * @param source
     * @param destination
     * @param date
     * @return
     */
    private FlightAndScheduleInfoDto[] fetchFlights(String source, String destination, String date) {
        FlightAndScheduleInfoDto[] flights = new FlightAndScheduleInfoDto[4];
        FlightAndScheduleInfoDto flight = new FlightAndScheduleInfoDto();
        flight.setFlightNumber(new BigInteger("1100"));
        flight.setArrivalTime("22:30");
        flight.setDepartureTime("23:30");
        flight.setCarrierName("Indigo");
        flight.setFlightModel("AirBus101");
        flight.setSeatCost(3000);

        flights[0] = flight;

        flight = new FlightAndScheduleInfoDto();
        flight.setFlightNumber(new BigInteger("1111"));
        flight.setArrivalTime("20:30");
        flight.setDepartureTime("21:30");
        flight.setCarrierName("AirIndia");
        flight.setFlightModel("AirBus101");
        flight.setSeatCost(5000);

        flights[1] = flight;

        flight.setFlightNumber(new BigInteger("7373"));
        flight.setArrivalTime("18:30");
        flight.setDepartureTime("19:30");
        flight.setCarrierName("Indigo");
        flight.setFlightModel("AirBus101");
        flight.setSeatCost(7000);

        flights[2] = flight;

        flight.setFlightNumber(new BigInteger("2311"));
        flight.setArrivalTime("20:00");
        flight.setDepartureTime("21:00");
        flight.setCarrierName("Spice Jet");
        flight.setFlightModel("Boeing101");
        flight.setSeatCost(30000);

        flights[3] = flight;
        return flights;
    }

    /***
     * fetch the booking details from database and send response with booking details
     * @param bookingId
     * @return
     */
    @GetMapping("/get/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") BigInteger bookingId) {
        Booking booking = bookingService.viewBooking(bookingId);
        ResponseEntity<Booking> response = new ResponseEntity<>(booking, HttpStatus.OK);
        return response;
    }

    /***
     * fetch all bookings from database
     * @return Booking List as response
     */
    @GetMapping
    public ResponseEntity<List<Booking>> fetchAllBookings() {
        List<Booking> bookings = bookingService.viewAllBooking();
        ResponseEntity<List<Booking>> response = new ResponseEntity<>(bookings, HttpStatus.OK);
        return response;
    }

    /***
     * delete booking if exist
     * @param bookingId
     * @return response as true or false
     */

    /***
     * fetch all airports from airport service url
     * @return
     */
    @GetMapping("/airports")
    public ResponseEntity<AirportDetailsDto[]> fetchAllAirports() {
        String url = airportServiceBaseUrl;
        AirportDetailsDto[] airports = restTemplate.getForObject(url, AirportDetailsDto[].class);
        ResponseEntity<AirportDetailsDto[]> response = new ResponseEntity<>(airports, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<Boolean> deleteBookingById(@PathVariable("bookingId") BigInteger bookingId) {
        Booking booking = bookingService.viewBooking(bookingId);
        ResponseEntity<Boolean> response;
        if (booking != null) {
            Boolean result = bookingService.deleteBooking(bookingId);
            response = new ResponseEntity<>(result, HttpStatus.OK);
            acknowledgeCancelBooking(booking);
            List<BigInteger> passengerUINList = booking.getPassengersUINList();
            cancelRequestPassengerStore(passengerUINList);
        } else
            response = new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        return response;
    }

    /***
     * send acknowledge to flight Scheduler to cancel booking
     * @param booking
     */
    private void acknowledgeCancelBooking(Booking booking) {
/*
        String url = flightScheduleServiceBaseUrl+"/cancelbooking";
        restTemplate.put(url,booking);
*/
    }

    /***
     * send req to Passenger service for cancel booking
     * @param passengerUINList
     */
    private void cancelRequestPassengerStore(List<BigInteger> passengerUINList) {
/*
        String url = passengerServiceBaseUrl+"/remove";
        restTemplate.put(url,passengerUINList);
*/
    }

}
