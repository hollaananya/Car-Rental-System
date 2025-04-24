package main.java.model;

// Daily Rental Pricing Strategy
public class DailyRentalPricing implements RentalPricingStrategy {

    private static final double DAILY_RATE = 20.0;  // Daily rental rate
    private static final double WEEKEND_RATE = 30.0;  // Weekend rate (more expensive than daily rate)

    @Override
    public double calculatePrice(int rentalDuration, int weekendDays) {
        // Calculate weekday price
        double weekdayPrice = DAILY_RATE * (rentalDuration - weekendDays);

        // Calculate weekend price (weekendDays are more expensive)
        double weekendPrice = WEEKEND_RATE * weekendDays;

        // Total rental cost
        return weekdayPrice + weekendPrice;
    }
}
