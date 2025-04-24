package main.java.model;

// Weekend Rental Pricing Strategy (more expensive than daily rate)
public class WeekendRentalPricing implements RentalPricingStrategy {

    // private static final double DAILY_RATE = 20.0;  // Regular daily rate
    private static final double WEEKEND_RATE = 30.0;  // Extra rate for weekends

    @Override
    public double calculatePrice(int rentalDuration, int weekendDays) {
        // Calculate weekday price
        //double weekdayPrice = DAILY_RATE * (rentalDuration - weekendDays);

        // Weekend price is calculated by applying the weekend rate for weekend days
        double weekendPrice = WEEKEND_RATE * weekendDays;

        return  weekendPrice;
    }
}
