import { Passenger } from './passenger';

export class Booking{
    userId:Number;
    source:String;
    destination:String;
    bookingDate:Date;//check the type of date
    passengers:Passenger[];
    ticketCost:Number;
    flightNumber:Number;
    numberOfPassenger:Number;
    billingAddress:String;

    constructor(userId:Number,source:String,destination:String,bookingDate:Date,passengers:Passenger[],ticketCost:Number,
        flightNumber:Number,numberOfPassenger:Number,billingAddress:String){
            this.userId=userId;
            this.source=source;
            this.destination=destination;
            this.bookingDate=bookingDate;
            this.passengers=passengers;
            this.ticketCost=ticketCost;
            this.flightNumber=flightNumber;
            this.numberOfPassenger=numberOfPassenger;
            this.billingAddress=billingAddress;
    }
}