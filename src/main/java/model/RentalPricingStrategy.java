package main.java.model;

// Strategy interface for calculating rental price
public interface RentalPricingStrategy {
    double calculatePrice(int rentalDuration, int weekendDays);
}
