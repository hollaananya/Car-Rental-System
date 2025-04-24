# Car Rental System

A simple car rental system built using JavaFX. The system allows users to rent, add, and return cars, as well as calculate rental costs based on daily and weekend rates. The project follows the **Model-View-Controller (MVC)** architecture and utilizes various design patterns to ensure modularity, flexibility, and maintainability.

## Features

### Major Use Cases:
1. **Add Car**: Add new cars to the rental fleet with details like Car ID and Car Model.
2. **Rent Car**: Rent a car for a specified duration and calculate rental cost based on the chosen pricing strategy (Daily/Weekend).
3. **Return Car**: Return a rented car and mark it as available for other users.
4. **View Available Cars**: View all available cars for rent.

### Minor Use Cases:
1. **Search Cars by Model**: Search for cars based on the model.
2. **View Car Details**: View detailed information about a specific car (ID, Model, Availability).
3. **View All Rented Cars**: (Admin Only) View all cars that are currently rented.
4. **Calculate Rental Cost**: Estimate the total rental cost based on the rental duration and number of weekend days.

## Technologies Used

- **JavaFX**: Used to create the graphical user interface.
- **Maven**: Project management and build tool.
- **Design Patterns**: 
  - **Strategy Pattern**: For pricing strategy (Daily/Weekend pricing).
  - **Command Pattern**: For handling actions like Add, Rent, and Return Car.
  - **Singleton Pattern**: For managing the RentalService (only one instance).
  - **Factory Pattern**: For creating car objects.

### Prerequisites

Make sure you have the following installed on your machine:
- **Java JDK 21** 
- **Maven** (for project management and building)
- **JavaFX SDK 21.0.7** (for UI components)
