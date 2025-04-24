package main.java.model;

public class ReturnCarCommand implements CarRentalCommand {
    private Car car;

    public ReturnCarCommand(Car car) {
        this.car = car;
    }

    @Override
    public void execute() {
        if (car == null) {
            System.err.println("Error: Car cannot be null.");
            return;
        }

        if (car.isAvailable()) {
            System.err.println("Error: Car " + car.getCarId() + " is not currently rented.");
            return;
        }

        // Return the car
        car.setAvailable(true);
        System.out.println(
            "Successfully returned car - " + car.getCarDetails() + 
            "\nCar is now available for rent."
        );
    }

    // Getter for testing/validation
    public Car getCar() {
        return car;
    }
}