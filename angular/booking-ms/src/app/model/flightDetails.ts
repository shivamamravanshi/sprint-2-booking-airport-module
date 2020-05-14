export class FligtDetails{
    flightNumber:Number;
    carrierName:String;
    flightModel:String;
    departure:String;
    arrival:String;
    cost:Number;

    constructor(flightNumber:Number,carrierName:String,flightModel:String,departure:String,arrival:String,cost:Number){
        this.flightNumber=flightNumber;
        this.carrierName=carrierName;
        this.flightModel=flightModel;
        this.departure=departure;
        this.arrival=arrival;
        this.cost=cost;
    }
}