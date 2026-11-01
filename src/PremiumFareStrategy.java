public class PremiumFareStrategy implements FareStrategy{
    
    @Override 
    public double calculateFare(double distance){
        double baseFare = 20.0;
        double perKmCharge = 2.0;

        return baseFare + (perKmCharge * distance);
    }
}
