package main.java.model;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private static RentalService instance;
    private List<Car> cars;

    private RentalService() {
        cars = new ArrayList<>();
    }

    public static RentalService getInstance() {
        if (instance == null) {
            instance = new RentalService();
        }
        return instance;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    // Corrected method to return car by ID
    public Car getCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {  // Changed from getCarDetails() to getCarId()
                return car;
            }
        }
        return null;
    }
}
