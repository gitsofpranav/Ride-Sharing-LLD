
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RideService {

    private final List<Rider> riders = new ArrayList<>();
    private final List<Driver> drivers = new ArrayList<>();
    private final List<Ride> rides = new ArrayList<>();

    private final Map<RideType, FareStrategy> fareStrategies =
            new EnumMap<>(RideType.class);

    public RideService() {
        fareStrategies.put(
            RideType.ECONOMY,
            new EconomyFareStrategy()
        );

        fareStrategies.put(
            RideType.PREMIUM,
            new PremiumFareStrategy()
        );

        fareStrategies.put(
            RideType.LUXURY,
            new LuxuryFareStrategy()
        );
    }

    // Register Rider
    public void registerRider(Rider rider) {
        riders.add(rider);
    }

    // Register Driver
    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }

    // Request a Ride
    public Ride requestRide(
            Rider rider,
            Location pickup,
            Location dropoff,
            RideType type) {

        Driver driver = findNearestDriver(pickup);

        if (driver == null) {
            System.out.println("No driver available!");
            return null;
        }

        Ride ride = new Ride(
            generateId(),
            rider,
            pickup,
            dropoff,
            type
        );

        ride.assignDriver(driver);
        rides.add(ride);

        return ride;
    }

    // Find Nearest Available Driver
    public Driver findNearestDriver(Location pickup) {

        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {

            if (!driver.isAvailable()
                    || driver.getCurrentLocation() == null) {
                continue;
            }

            double distance =
                pickup.distanceTo(driver.getCurrentLocation());

            if (distance < minDistance) {
                minDistance = distance;
                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }

    // Calculate Fare using Strategy Pattern
    public double calculateFare(Ride ride) {

        FareStrategy strategy =
            fareStrategies.get(ride.getType());

        if (strategy == null) {
            throw new IllegalArgumentException(
                "No fare strategy available"
            );
        }

        double distance =
            ride.getPickup().distanceTo(ride.getDropoff());

        return strategy.calculateFare(distance);
    }

    public void acceptRide(Ride ride) {
    ride.accept();
    System.out.println("Driver accepted the ride!");
}

    // Complete Ride
    public void completeRide(Ride ride) {

        double fare = calculateFare(ride);

        ride.complete(fare);

        System.out.println("Ride completed!");
        System.out.println("Final fare: " + fare);
    }

    // Get Ride History
    public List<Ride> getAllRides() {
        return new ArrayList<>(rides);
    }

    // Generate Unique Ride ID
    private String generateId() {
        return "RIDE-" + UUID.randomUUID();
    }


    public void rejectRide(Ride ride) {
    ride.rejectDriver();

    Driver nextDriver = findNearestDriver(ride.getPickup());

    if (nextDriver == null) {
        System.out.println("No more drivers available!");
        return;
    }

    ride.assignDriver(nextDriver);

    System.out.println("Ride reassigned to: " + nextDriver.getname());
}
}
