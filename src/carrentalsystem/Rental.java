package carrentalsystem;

public class Rental {
    private User user;
    private Car car;
    private String rentalDate;
    private int rentalDays;
    private double totalCharge;

    public Rental(User user, Car car, String rentalDate, int rentalDays) {
        this.user = user;
        this.car = car;
        this.rentalDate = rentalDate;
        this.rentalDays = rentalDays;
        this.totalCharge = rentalDays * car.getDailyRate();
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCharge() {
        return totalCharge;
    }
}
