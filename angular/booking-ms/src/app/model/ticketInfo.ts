import { Passenger } from './passenger';

export class TicketInfo {
    bookingId: Number;
    userId: Number;
    source: String;
    destination: String;
    bookingDate: String;
    passengerList: Passenger[];
    ticketCost: Number;
    flightNumber: Number;
    arrivalTime: String;
    departureTime: String;
    numberOfPassengers: Number;
    billingAddress: String;
    contactNumber: Number

    constructor(bookingId: Number, userId: Number, source: String, destination: String, arrivalTime: String,
        departureTime: String, bookingDate: String, passengerList: Passenger[], ticketCost: Number,
        flightNumber: Number, numberOfPassengers: Number, billingAddress: String, contactNumber: Number) {
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.bookingDate = bookingDate;
        this.passengerList = passengerList;
        this.ticketCost = ticketCost;
        this.flightNumber = flightNumber;
        this.numberOfPassengers = numberOfPassengers;
        this.billingAddress = billingAddress;
        this.contactNumber = contactNumber;
        this.bookingId = bookingId;
    }
}