import { Passenger } from './passenger';

export class BookingDetails{
    flightNumber:Number;
    source:String;
    destination:String;
    arrivalTime:String;
    departureTime:String;
    passengerList:Passenger[];
    userPhone:Number;
    userId:Number;
    billingAddress:String;

    constructor(flightNumber:Number,source:String,destination:String,arrivalTime:String,departureTime:String,
        passengerList:Passenger[],userPhone:Number,userId:Number,billingAddress:String){
            this.flightNumber=flightNumber;
            this.source=source;
            this.destination=destination;
            this.arrivalTime=arrivalTime;
            this.departureTime=departureTime;
            this.passengerList=passengerList;
            this.userPhone=userPhone;
            this.billingAddress=billingAddress;
        }
}