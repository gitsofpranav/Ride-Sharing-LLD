public class RideSharingApp {
    public static void main(String[] args) {
        RideService service = new RideService();
        Rider rider = new Rider("R1", "Rahul", "7894512366");
        Driver driver = new Driver("D1", "Ramu", "7891159755", "PB01AB1234", new Location(30.7046, 76.7179));

        service.registerRider(rider);
        service.registerDriver(driver);

        Location pickup = new Location(30.7333, 76.7794);
        Location dropoff = new Location(30.7410, 76.7680);

        Ride ride = service.requestRide(rider, pickup, dropoff, RideType.ECONOMY);

        if (ride != null) {

            System.out.println("Ride booked successfully!");

            System.out.println("Ride ID: " + ride.getId());

            System.out.println("Driver: " +
                    ride.getDriver().getname());

            System.out.println("Status: " +
                    ride.getStatus());

            // 7. Accept Ride
            service.acceptRide(ride);

            // 8. Start Ride
            ride.start();

            System.out.println("Ride started!");

            // 9. Complete Ride
            service.completeRide(ride);

            System.out.println("Final Status: " +
                    ride.getStatus());

        } else {
            System.out.println("Ride booking failed!");
        }

         Rider secondRider = new Rider("R2", "Amit", "9876543211");
         service.registerRider(secondRider);

         Ride secondRide = service.requestRide(
                 secondRider,
                 new Location(30.7350, 76.7800),
                 new Location(30.7500, 76.7900),
                 RideType.PREMIUM);

         if (secondRide != null) {
             System.out.println("\nSecond Ride Booked!");
             System.out.println("Driver: " + secondRide.getDriver().getname());
             System.out.println("Status: " + secondRide.getStatus());
         }
    }
}
