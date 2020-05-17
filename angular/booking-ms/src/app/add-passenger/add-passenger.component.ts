import { Component, OnInit } from '@angular/core';
import { Passenger } from '../model/passenger'
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Booking } from '../model/booking';
import { BookingService } from '../services/bookingservice';
import { Observable } from 'rxjs';
import { TicketInfo } from '../model/ticketInfo';
@Component({
  selector: 'app-add-passenger',
  templateUrl: './add-passenger.component.html',
  styleUrls: ['./add-passenger.component.css']
})
export class AddPassengerComponent implements OnInit {

  service: BookingService;

  route: ActivatedRoute;
  constructor(route: ActivatedRoute, service: BookingService) {
    this.route = route;
    this.service = service;
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      (params: ParamMap) => {
        let source: String = params.get("source");
        let destination: String = params.get("destination");
        let bookingDate = params.get("date");
        let arrivalTime = params.get("arrivalTime");
        let departureTime = params.get("departureTime");
        this.bookingDate = bookingDate;
        this.source = source;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
      }
    );
  }

  getLoginUserId() {
    return 10;
  }

  source: String = null;
  destination: String = null;
  arrivalTime: String = null;
  departureTime: String = null;
  bookingDate = null;
  flightNumber: Number = null;
  ticketCost: Number;



  ticketInfo: TicketInfo = null;
  passengers: Passenger[] = [];
  returnPassengers: Passenger[] = []

  addPassenger(passengerForm: any) {
    let details = passengerForm.value;
    let passengerList: Passenger[] = [];

    let passengerUIN1 = details.uin1;
    let passengerName1 = details.pname1;
    let passengerAge1 = details.passenger_age1;
    let passengerGender1 = details.gender1;
    let luggage1 = details.luggage1;

    let passenger = new Passenger(null, passengerUIN1, passengerName1, passengerAge1, passengerGender1, luggage1);

    passengerList.push(passenger);

    let passengerUIN2 = details.uin2;
    let passengerName2 = details.pname2;
    let passengerAge2 = details.passenger_age2;
    let passengerGender2 = details.gender2;
    let luggage2 = details.luggage2;

    if (passengerUIN2 !== null && passengerName2 !== '' && passengerGender2 !== '' && passengerAge2 !== null && luggage2 !== null) {
      passenger = new Passenger(null, passengerUIN2, passengerName2, passengerAge2, passengerGender2, luggage2);
      passengerList.push(passenger);
    }

    let passengerUIN3 = details.uin3;
    let passengerName3 = details.pname3;
    let passengerAge3 = details.passenger_age3;
    let passengerGender3 = details.gender3;
    let luggage3 = details.luggage3;

    if (passengerUIN3 !== null && passengerName3 !== '' && passengerGender3 !== '' && passengerAge3 !== null && luggage3 !== null) {
      passenger = new Passenger(null, passengerUIN3, passengerName3, passengerAge3, passengerGender3, luggage3);
      passengerList.push(passenger);
    }

    let passengerUIN4 = details.uin4;
    let passengerName4 = details.pname4;
    let passengerAge4 = details.passenger_age4;
    let passengerGender4 = details.gender4;
    let luggage4 = details.luggage4;

    if (passengerUIN4 !== null && passengerName4 == '' && passengerGender4 !== '' && passengerAge4 !== null && luggage4 !== null) {
      passenger = new Passenger(null, passengerUIN4, passengerName4, passengerAge4, passengerGender4, luggage4);
      passengerList.push(passenger);
    }

    let billingAddress: String = details.billingAddress;
    let contactNumber: Number = details.contactNumber;

    this.createBooking(passengerList, billingAddress, contactNumber);

    passengerForm.reset();
  }

  createBooking(passengerList: Passenger[], billingAddress: String, contactNumber: Number) {
    //let flightNumber:Number = this.flightNumber;
    //let source:String = this.source;
    //let destination:String = this.destination;
    //let arrivalTime=this.arrivalTime;
    //let departureTime = this.departureTime;
    //let bookingDate = this.bookingDate;
    let flightNumber: Number = 1212;
    let source: String = "Mumbai";
    let destination: String = "Delhi";
    let arrivalTime: String = "20:30";
    let departureTime = "21:30";
    let bookingDate = "20-09-2020";
    let userID = this.getLoginUserId();
    let ticketCost = 7000;
    let numberOfpassenger = passengerList.length;

    let booking: Booking = new Booking(userID, source, destination, arrivalTime, departureTime, bookingDate, passengerList, ticketCost, flightNumber,
      numberOfpassenger, billingAddress, contactNumber);



    let observable: Observable<TicketInfo> = this.service.createBooking(booking);
    observable.subscribe((ticketInfo: TicketInfo) => {
      this.ticketInfo = ticketInfo;
      this.returnPassengers = ticketInfo.passengerList;
      this.service.cacheTicketInfo(ticketInfo);
    },
      err => {
        console.log("Error" + err);
      });

  }




}
