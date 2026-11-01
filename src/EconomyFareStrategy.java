public class EconomyFareStrategy implements FareStrategy{

    @Override
    public double calculateFare(double distance) {
        double baseFare = 10.0;
        double perKmCharge = 2.0;

        return baseFare + (distance * perKmCharge);
    }
}
