
import java.time.Instant;

public class Ride {

    private final String id;
    private final Rider rider;
    private Driver driver;

    private final Location pickup;
    private final Location dropoff;
    private final RideType type;

    private RideStatus status;
    private double fare;

    private final Instant requestTime;
    private Instant startTime;
    private Instant endTime;

    public Ride(
            String id,
            Rider rider,
            Location pickup,
            Location dropoff,
            RideType type) {

        this.id = id;
        this.rider = rider;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.type = type;

        this.status = RideStatus.REQUESTED;
        this.fare = 0.0;
        this.requestTime = Instant.now();
    }

    public void assignDriver(Driver driver) {
        if (status != RideStatus.REQUESTED) {
            throw new IllegalStateException("Ride is not awaiting a driver");
        }

        if (!driver.isAvailable()) {
            throw new IllegalStateException("Driver is not available");
        }

        this.driver = driver;
        driver.setAvailable(false);
    }

    public void start() {
        if (status != RideStatus.ACCEPTED) {
            throw new IllegalStateException(
                    "Only an accepted ride can start");
        }

        status = RideStatus.STARTED;
        startTime = Instant.now();
    }

    public void complete(double calculatedFare) {
        if (status != RideStatus.STARTED) {
            throw new IllegalStateException(
                    "Only a started ride can be completed");
        }

        if (calculatedFare < 0) {
            throw new IllegalArgumentException(
                    "Fare cannot be negative");
        }

        fare = calculatedFare;
        status = RideStatus.COMPLETED;
        endTime = Instant.now();

        driver.setAvailable(true);
    }

    public void rejectDriver() {
        if (status != RideStatus.REQUESTED) {
            throw new IllegalStateException("Ride is not awaiting a driver");
        }

        if (driver == null) {
            throw new IllegalStateException("No driver is assigned");
        }

        driver.setAvailable(true);
        driver = null;
    }

    public void accept() {
        if (status != RideStatus.REQUESTED) {
            throw new IllegalStateException("Ride is not awaiting acceptance");
        }

        if (driver == null) {
            throw new IllegalStateException("No driver assigned");
        }

        status = RideStatus.ACCEPTED;
    }

    

    public String getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getPickup() {
        return pickup;
    }

    public Location getDropoff() {
        return dropoff;
    }

    public RideType getType() {
        return type;
    }

    public RideStatus getStatus() {
        return status;
    }

    public double getFare() {
        return fare;
    }

    public Instant getRequestTime() {
        return requestTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
