export class Passenger{
    
    pnr:Number;
    passengerUIN:Number;
    passengerName:String;
    passengerAge:Number;
    gender:String;

    constructor(pnr:Number,passengerUIN:Number,passengerName:String,passengerAge:Number,gender:String){
        this.pnr=pnr;
        this.passengerUIN=passengerUIN;
        this.passengerName=passengerName;
        this.passengerAge=passengerAge;
        this.gender=gender;
    }
}