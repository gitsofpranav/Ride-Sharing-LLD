public abstract class User {
    private final String id;
    private final String name;
    private final String phone;
    private double rating;

    User(String id, String name, String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.rating = 5.0;

    }


    public String getId(){
        return id;
    }

    public String getname(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public  double getRating(){
        return rating;
    }

    public void updateRating(double newRating){
        if(newRating < 1.0 || newRating > 5.0){
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        this.rating = newRating;
    }
}
