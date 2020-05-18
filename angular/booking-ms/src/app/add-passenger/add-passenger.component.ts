import { Component, OnInit } from '@angular/core';
import { Passenger } from '../model/passenger'
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Booking } from '../model/booking';
import { BookingService } from '../services/bookingservice';
import { Observable } from 'rxjs';
import { TicketInfo } from '../model/ticketInfo';
import { FligtDetails } from '../model/flightDetails';
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
    this.service.getFlightInfo().subscribe(data => {
      this.flightInfo = data;
    },
      err => {
        console.log("Error" + err);
      });

    this.service.getBookingInfo().subscribe(data => {
      this.source = data.source;
      this.destination = data.destination;
      this.bookingDate = data.bookingDate;
    },
      err => {
        console.log("Error" + err);
      })
  }

  flightInfo: FligtDetails;
  bookingDate: String;
  source: String;
  destination: String;

  getLoginUserId() {
    return 10;
  }


  ticketInfo: TicketInfo = null;
  passengers: Passenger[] = [];

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

    if (passengerUIN4 !== null && passengerName4 !== '' && passengerGender4 !== '' && passengerAge4 !== null && luggage4 !== null) {
      passenger = new Passenger(null, passengerUIN4, passengerName4, passengerAge4, passengerGender4, luggage4);
      passengerList.push(passenger);
    }

    let billingAddress: String = details.billingAddress;
    let contactNumber: Number = details.contactNumber;

    this.createBooking(passengerList, billingAddress, contactNumber);

    passengerForm.reset();
  }

  createBooking(passengerList: Passenger[], billingAddress: String, contactNumber: Number) {
    let flightNumber = this.flightInfo.flightNumber;
    let departureTime = this.flightInfo.departureTime;
    let arrivalTime = this.flightInfo.arrivalTime;
    let ticketCost = this.flightInfo.seatCost;
    let bookingDate = this.bookingDate;
    let source: String = this.source;
    let destination: String = this.destination;
    let userID = this.getLoginUserId();

    let numberOfpassenger = passengerList.length;

    let booking: Booking = new Booking(userID, source, destination, arrivalTime, departureTime, bookingDate, passengerList, ticketCost, flightNumber,
      numberOfpassenger, billingAddress, contactNumber);



    let observable: Observable<TicketInfo> = this.service.createBooking(booking);
    observable.subscribe((ticketInfo: TicketInfo) => {
      this.ticketInfo = ticketInfo;
      this.passengers = ticketInfo.passengerList;
      this.service.cacheTicketInfo(ticketInfo);
    },
      err => {
        console.log("Error" + err.Error);
      });

  }




}
