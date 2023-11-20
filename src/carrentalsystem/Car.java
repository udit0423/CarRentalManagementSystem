package carrentalsystem;

public class Car{
    
    private String carName;
    private double dailyRate;
    private String carType;

    public Car(String carName, String carType, double dailyRate) {
        this.carName = carName;
        this.carType = carType;
        this.dailyRate = dailyRate;
    }
    public String getCarName() {
        return carName;
    }

    public String getCarType() {
        return carType;
    }

    public double getDailyRate() {
        return dailyRate;
    }
}
