import { Component, OnInit } from '@angular/core';
import {Passenger} from '../model/passenger'
@Component({
  selector: 'app-add-passenger',
  templateUrl: './add-passenger.component.html',
  styleUrls: ['./add-passenger.component.css']
})
export class AddPassengerComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  addedPassenger: Passenger=null;

  addPassenger(passengerForm:any){
    let details = passengerForm.value;
    let passengerUIN  = details.uin;
    let passengerName = details.pname;
    let passengerAge = details.passenger_age;
    let gender = details.gender;
    let luggage = details.luggage;
    
    let passenger = new Passenger(null,passengerUIN,passengerName,passengerAge,gender,luggage);
    this.addedPassenger=passenger;
    passengerForm.reset();
  }

}
