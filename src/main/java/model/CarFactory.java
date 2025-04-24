package main.java.model;

public class CarFactory {
    // The factory method now takes availability as an argument
    public Car createCar(String carId, String carModel, boolean available) {
        return new Car(carId, carModel, available);
    }
}
