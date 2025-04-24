package main.java.model;

public class Car {
    private String carId;
    private String carModel;
    private boolean available;

    public Car(String carId, String carModel, boolean available) {
        this.carId = carId;
        this.carModel = carModel;
        this.available = available;
    }

    // Getter for carId
    public String getCarId() {
        return carId;
    }

    // Getter for carModel
    public String getCarModel() {
        return carModel;
    }

    // Getter and setter for available status
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Returns a string with car details
    public String getCarDetails() {
        return "ID: " + carId + ", Model: " + carModel + ", Available: " + available;
    }
}
