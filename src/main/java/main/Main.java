package main.java.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.java.controller.CarRentalController;  // Import the controller

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an instance of the CarRentalController
        CarRentalController controller = new CarRentalController();

        // Create UI components
        Text welcomeText = new Text("Welcome to the Car Rental System");

        // Labels for input fields
        Label carIdLabel = new Label("Car ID:");
        Label carModelLabel = new Label("Car Model:");
        Label rentalDurationLabel = new Label("Rental Duration (in days):");
        Label dailyDaysLabel = new Label("Daily Days:");
        Label weekendDaysLabel = new Label("Weekend Days:");
        Label carDetailsLabel = new Label("Car Details");

        // Text fields for user input
        TextField carIdField = new TextField();
        TextField carModelField = new TextField();
        TextField rentalDurationField = new TextField();
        TextField dailyDaysField = new TextField();
        TextField weekendDaysField = new TextField();

        // ComboBox for pricing strategy
        ComboBox<String> pricingStrategyBox = new ComboBox<>();
        pricingStrategyBox.getItems().addAll("Daily", "Weekend");

        // Buttons for various actions
        Button addCarButton = new Button("Add Car");
        Button rentCarButton = new Button("Rent Car");
        Button returnCarButton = new Button("Return Car");
        Button viewCarsButton = new Button("View Available Cars");
        Button searchCarByModelButton = new Button("Search Cars by Model");
        Button viewCarDetailsButton = new Button("View Car Details");
        Button viewRentedCarsButton = new Button("View All Rented Cars");
        Button calculateRentalCostButton = new Button("Calculate Rental Cost");

        // Button actions (these should call the corresponding methods in your controller)
        addCarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleAddCar(carIdField, carModelField, carDetailsLabel);
            }
        });

        rentCarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleRentCar(carIdField, rentalDurationField, weekendDaysField, pricingStrategyBox, carDetailsLabel);
            }
        });

        returnCarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleReturnCar(carIdField, carDetailsLabel);
            }
        });

        viewCarsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleViewCars(carDetailsLabel);
            }
        });

        searchCarByModelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleSearchCarByModel(carModelField, carDetailsLabel);
            }
        });

        viewCarDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleViewCarDetails(carIdField, carDetailsLabel);
            }
        });

        // New Minor Use Case: View All Rented Cars
        viewRentedCarsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleViewAllRentedCars(carDetailsLabel);
            }
        });

        // New Minor Use Case: Calculate Rental Cost
        calculateRentalCostButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleCalculateRentalCost(dailyDaysField, weekendDaysField, carDetailsLabel);
            }
        });

        // Layout: GridPane for organizing components
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(welcomeText, 0, 0, 2, 1);
        grid.add(carIdLabel, 0, 1);
        grid.add(carIdField, 1, 1);
        grid.add(carModelLabel, 0, 2);
        grid.add(carModelField, 1, 2);
        grid.add(rentalDurationLabel, 0, 3);
        grid.add(rentalDurationField, 1, 3);
        grid.add(dailyDaysLabel, 0, 4);
        grid.add(dailyDaysField, 1, 4);
        grid.add(weekendDaysLabel, 0, 5);
        grid.add(weekendDaysField, 1, 5);
        grid.add(addCarButton, 0, 6);
        grid.add(rentCarButton, 1, 6);
        grid.add(returnCarButton, 0, 7);
        grid.add(viewCarsButton, 1, 7);
        grid.add(searchCarByModelButton, 0, 8);
        grid.add(viewCarDetailsButton, 1, 8);
        grid.add(viewRentedCarsButton, 0, 9);
        grid.add(calculateRentalCostButton, 1, 9);
        grid.add(carDetailsLabel, 0, 10, 2, 1);

        // Create a scene and add it to the stage
        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setTitle("Car Rental System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
