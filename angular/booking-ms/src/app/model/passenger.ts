export class Passenger{
    
    pnr:Number;
    passengerUIN:Number;
    passengerName:String;
    passengerAge:Number;
    gender:String;
    luggage:Number;

    constructor(pnr:Number,passengerUIN:Number,passengerName:String,passengerAge:Number,gender:String,luggage:Number){
        this.pnr=pnr;
        this.passengerUIN=passengerUIN;
        this.passengerName=passengerName;
        this.passengerAge=passengerAge;
        this.gender=gender;
        this.luggage=luggage;
    }
}