import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Booking } from '../model/booking';
import { Observable, BehaviorSubject } from 'rxjs';
import { Airport } from '../model/airport';
import { FligtDetails } from '../model/flightDetails';
import { TicketInfo } from '../model/ticketInfo';

@Injectable()
export class BookingService {

    ticketInfo: TicketInfo = undefined;
    baseBookingUrl = "http://localhost:8991/bookings";
    ticketInfoSubject = new BehaviorSubject(null);
    flightInfoSubject = new BehaviorSubject(null);
    bookingInfoSubject = new BehaviorSubject(null);
    client: HttpClient;



    constructor(client: HttpClient) {
        this.client = client;
    }


    createBooking(booking: Booking): Observable<TicketInfo> {
        let url = this.baseBookingUrl + "/new";
        let observable: Observable<TicketInfo> = this.client.post<TicketInfo>(url, booking);
        return observable;
    }

    fetchAllBookings(): Observable<Booking[]> {
        let url = this.baseBookingUrl;
        let observable: Observable<Booking[]> = this.client.get<Booking[]>(url);
        return observable;
    }

    fetchAllAirports(): Observable<Airport[]> {
        let url = this.baseBookingUrl + "/airports";
        let observable: Observable<Airport[]> = this.client.get<Airport[]>(url);
        return observable;
    }

    fetchSearchedFlights(source: String, destination: String, date: String): Observable<FligtDetails[]> {
        let url = this.baseBookingUrl + "/flightsearch/" + source + "/" + destination + "/" + date;
        let observable: Observable<FligtDetails[]> = this.client.get<FligtDetails[]>(url);
        return observable;
    }

    findBookingById(id: number): Observable<Booking> {
        let url = this.baseBookingUrl + '/get/' + id;
        let observable: Observable<Booking> = this.client.get<Booking>(url);
        return observable;
    }

    /**
    * fires delete request to server to delete booking by id mentioned in url
    * @param id of booking which has to be deleted
    */
    deleteBookingById(id: number) {
        let url = this.baseBookingUrl + "/delete/" + id;
        let result: Observable<boolean> = this.client.delete<boolean>(url);
        return result;
    }

    cacheTicketInfo(ticketInfo: TicketInfo) {
        this.ticketInfoSubject.next(ticketInfo);
    }

    getTicketInfo() {
        return this.ticketInfoSubject.asObservable();
    }

    cacheFlightInfo(flight: FligtDetails) {
        this.flightInfoSubject.next(flight);
    }

    getFlightInfo() {
        return this.flightInfoSubject.asObservable();
    }

    cacheBookingInfo(source: String, destination: String, bookingDate: String) {
        this.bookingInfoSubject.next({
            source,
            destination,
            bookingDate
        });
    }

    getBookingInfo() {
        return this.bookingInfoSubject.asObservable();
    }

}