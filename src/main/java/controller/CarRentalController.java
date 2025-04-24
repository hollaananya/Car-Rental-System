package main.java.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import main.java.model.*;

public class CarRentalController {
    private RentalService rentalService;
    private RentalPricingStrategy rentalPricing;

    public CarRentalController() {
        rentalService = RentalService.getInstance();
    }

    // Major Use Case: Add Car
    public void handleAddCar(TextField carIdField, TextField carModelField, Label carDetailsLabel) {
        String carId = carIdField.getText();
        String carModel = carModelField.getText();
        CarFactory carFactory = new CarFactory();

        // Create car with 'true' for availability (car is available when added)
        Car car = carFactory.createCar(carId, carModel, true);  // Availability is true by default
        AddCarCommand addCarCommand = new AddCarCommand(car, rentalService);
        addCarCommand.execute();
        carDetailsLabel.setText("Car Added: " + car.getCarDetails());
    }

    // Major Use Case: Rent Car
    public void handleRentCar(TextField carIdField, TextField rentalDurationField, TextField weekendDaysField, ComboBox<String> pricingStrategyBox, Label carDetailsLabel) {
        try {
            String carId = carIdField.getText();
            int rentalDuration = Integer.parseInt(rentalDurationField.getText());
            int weekendDays = Integer.parseInt(weekendDaysField.getText());  // Get weekend days from input
            Car car = rentalService.getCarById(carId);

            if (car == null) {
                carDetailsLabel.setText("Car not found.");
                return;
            }

            if (!car.isAvailable()) {
                carDetailsLabel.setText("Car already rented.");
                return;
            }

            // Get selected pricing strategy from dropdown
            String selectedPricing = pricingStrategyBox.getValue();
            if ("Daily".equalsIgnoreCase(selectedPricing)) {
                rentalPricing = new DailyRentalPricing();
            } else if ("Weekend".equalsIgnoreCase(selectedPricing)) {
                rentalPricing = new WeekendRentalPricing();
            }

            // Now pass the weekendDays parameter to RentCarCommand
            RentCarCommand rentCarCommand = new RentCarCommand(car, rentalDuration, weekendDays, rentalPricing);
            rentCarCommand.execute();
            carDetailsLabel.setText("Car rented: " + car.getCarDetails());

            // Clear input fields
            carIdField.clear();
            rentalDurationField.clear();
            weekendDaysField.clear();

        } catch (NumberFormatException e) {
            carDetailsLabel.setText("Invalid duration. Please enter a number.");
        }

        // Force UI update
        handleViewCars(carDetailsLabel);
    }

    // Major Use Case: Return Car
    public void handleReturnCar(TextField carIdField, Label carDetailsLabel) {
        String carId = carIdField.getText();
        if (carId == null || carId.isEmpty()) {
            carDetailsLabel.setText("Please enter a car ID.");
            return;
        }

        Car car = rentalService.getCarById(carId);
        if (car == null) {
            carDetailsLabel.setText("Car not found.");
            return;
        }

        if (car.isAvailable()) {
            carDetailsLabel.setText("Car is not currently rented.");
            return;
        }

        ReturnCarCommand returnCarCommand = new ReturnCarCommand(car);
        returnCarCommand.execute();
        carDetailsLabel.setText("Car returned: " + car.getCarDetails());
        carIdField.clear();
        handleViewCars(carDetailsLabel);
    }

    // Major Use Case: View Available Cars
    public void handleViewCars(Label carDetailsLabel) {
        StringBuilder carsList = new StringBuilder();
        for (Car car : rentalService.getCars()) {
            if (car.isAvailable()) {
                carsList.append(car.getCarDetails()).append("\n");
            }
        }
        carDetailsLabel.setText("Available Cars:\n" + carsList.toString());
    }

    // Minor Use Case: Search Cars by Model
    public void handleSearchCarByModel(TextField carModelField, Label carDetailsLabel) {
        String modelToSearch = carModelField.getText();
        StringBuilder searchResults = new StringBuilder();

        for (Car car : rentalService.getCars()) {
            if (car.getCarModel().equalsIgnoreCase(modelToSearch)) {
                searchResults.append(car.getCarDetails()).append("\n");
            }
        }

        if (searchResults.length() > 0) {
            carDetailsLabel.setText("Cars matching the model:\n" + searchResults.toString());
        } else {
            carDetailsLabel.setText("No cars found with model: " + modelToSearch);
        }
    }

    // Minor Use Case: View Car Details
    public void handleViewCarDetails(TextField carIdField, Label carDetailsLabel) {
        String carId = carIdField.getText();
        Car car = rentalService.getCarById(carId);

        if (car != null) {
            carDetailsLabel.setText("Car Details: " + car.getCarDetails());
        } else {
            carDetailsLabel.setText("No car found with ID: " + carId);
        }
    }

    // Minor Use Case: View All Rented Cars
    public void handleViewAllRentedCars(Label carDetailsLabel) {
        StringBuilder rentedCarsList = new StringBuilder();
        for (Car car : rentalService.getCars()) {
            if (!car.isAvailable()) {
                rentedCarsList.append(car.getCarDetails()).append("\n");
            }
        }

        if (rentedCarsList.length() > 0) {
            carDetailsLabel.setText("Rented Cars:\n" + rentedCarsList.toString());
        } else {
            carDetailsLabel.setText("No cars are currently rented.");
        }
    }

    // Minor Use Case: Calculate Rental Cost
    public void handleCalculateRentalCost(TextField dailyDaysField, TextField weekendDaysField, Label carDetailsLabel) {
        try {
            int dailyDays = Integer.parseInt(dailyDaysField.getText());
            int weekendDays = Integer.parseInt(weekendDaysField.getText());

            // Validate input values
            if (dailyDays < 0 || weekendDays < 0) {
                carDetailsLabel.setText("Please enter valid numbers for both daily and weekend days.");
                return;
            }

            // Apply daily and weekend pricing
            rentalPricing = new DailyRentalPricing(); // Use same logic for daily and weekend pricing
            double rentalCost = rentalPricing.calculatePrice(dailyDays + weekendDays, weekendDays);

            carDetailsLabel.setText("Estimated rental cost: $" + rentalCost);
        } catch (NumberFormatException e) {
            carDetailsLabel.setText("Invalid input. Please enter valid numbers.");
        }
    }
}
