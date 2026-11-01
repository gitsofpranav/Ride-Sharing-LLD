public class Location {
    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double distanceTo(Location other) {
        double latDiff = this.latitude - other.latitude;
        double lonDiff = this.longitude - other.longitude;

        return Math.sqrt(
            Math.pow(latDiff, 2) + Math.pow(lonDiff, 2)
        );
    }
}
