import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Booking } from '../model/booking';
import { Observable } from 'rxjs';
import { Airport } from '../model/airport';

@Injectable()
export class BookingService{

    client:HttpClient ;
    constructor(client:HttpClient){  
    this.client=client;
    }
    baseBookingUrl="http://localhost:8991/bookings";

    createBooking(booking:Booking): Observable<Booking>{
        let url = this.baseBookingUrl+"/new";
        let result:Observable<Booking>= this.client.post<Booking>(url,booking);
        return result;
    }

    fetchAllBookings():Observable<Booking[]>{
        let url=this.baseBookingUrl;
        let observable:Observable<Booking[]> =this.client.get<Booking[]>(url);
        return observable;
    }

    fetchAllAirports():Observable<Airport[]>{
        let url=this.baseBookingUrl+"/airports";
        let observable:Observable<Airport[]> = this.client.get<Airport[]>(url);
        return observable;
    }

    findBookingById(id:number):Observable<Booking>{
        let url=this.baseBookingUrl+'/get/'+id;
        let observable:Observable<Booking> =this.client.get<Booking>(url);
        return observable;  
    }

    /**
    * fires delete request to server to delete booking by id mentioned in url
    * @param id of booking which has to be deleted
    */
    deleteBookingById(id:number){
        let url=this.baseBookingUrl+"/delete/"+id;
        let result:Observable<boolean>=this.client.delete<boolean>(url);
        return result;
    }


}