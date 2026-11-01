public class LuxuryFareStrategy implements FareStrategy{
    
    @Override 
    public double calculateFare(double distance){
        double baseFare = 40.0;
        double perKmCharge = 5.0;

        return baseFare + (perKmCharge * distance);
    }
}
