package main.java.model;

public class RentCarCommand implements CarRentalCommand {
    private Car car;
    private int rentalDuration;
    private RentalPricingStrategy rentalPricing;
    private int weekendDays;

    // Constructor now accepts weekendDays
    public RentCarCommand(Car car, int rentalDuration, int weekendDays, RentalPricingStrategy rentalPricing) {
        this.car = car;
        this.rentalDuration = rentalDuration;
        this.weekendDays = weekendDays; // Add weekend days to the constructor
        this.rentalPricing = rentalPricing;
    }

    @Override
    public void execute() {
        if (car == null) {
            System.err.println("Error: Car cannot be null.");
            return;
        }

        if (rentalDuration <= 0) {
            System.err.println("Error: Rental duration must be positive.");
            return;
        }

        if (!car.isAvailable()) {
            System.err.println("Error: Car " + car.getCarId() + " is already rented.");
            return;
        }

        // Rent the car
        car.setAvailable(false);  // Set car as rented (not available)

        // Calculate rental price (pass both rentalDuration and weekendDays)
        double price = rentalPricing.calculatePrice(rentalDuration, weekendDays);
        
        System.out.println(
            "Successfully rented car - " + car.getCarDetails() + 
            "\nDuration: " + rentalDuration + " days" +
            "\nWeekend days: " + weekendDays + 
            "\nTotal price: $" + String.format("%.2f", price)
        );
    }

    // Getter for testing/validation
    public Car getCar() {
        return car;
    }

    // Getter for testing/validation
    public int getRentalDuration() {
        return rentalDuration;
    }

    // Getter for testing/validation
    public int getWeekendDays() {
        return weekendDays;
    }
}
