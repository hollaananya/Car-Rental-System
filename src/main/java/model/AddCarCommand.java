package main.java.model;

public class AddCarCommand implements CarRentalCommand {
    private Car car;
    private RentalService rentalService;

    public AddCarCommand(Car car, RentalService rentalService) {
        this.car = car;
        this.rentalService = rentalService;
    }

    @Override
    public void execute() {
        rentalService.addCar(car);
        System.out.println("Car added: " + car.getCarDetails());
    }
}
