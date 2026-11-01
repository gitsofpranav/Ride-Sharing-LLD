public class Driver extends User {
    private final String vehicleNumber;
    private Location currLocation;
    private boolean available;

    public Driver( String id,String name,String phone,String vehicleNumber,Location currentLocation){
        super(id, name, phone);
        this.vehicleNumber = vehicleNumber;
        this.currLocation = currentLocation;
        this.available = true;
    }

    public String getVechileNumber(){
        return vehicleNumber;
    }

    public Location getCurrentLocation(){
        return currLocation;
    }

    public boolean isAvailable(){
        return available;
    }

     public void updateLocation(Location location) {
        this.currLocation = location;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
