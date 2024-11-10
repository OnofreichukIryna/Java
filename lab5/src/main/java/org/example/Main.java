package org.example;

import Models.CarDB;
import Models.RentalDB;
import Models.RenterDB;
import Models.Car;
import Models.Rental;
import Models.Renter;
import DB.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection db = null;

        // Connecting to the database and creating tables
        try {
            db = DatabaseManager.connect();
            DatabaseManager.createTables();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }

        int carId = 0;
        int renterId = 0;

        // ** Working with Car table **
        CarDB carDB = new CarDB();
        Car car = new Car("Toyota", "JHG4536KFD", "CE1585EH", LocalDate.of(2020, 5, 20), 40000);

        try {
            // Adding a new car
            carDB.addCar(db, car);
            System.out.println("Car added: " + car);

            // Fetching car by VIN
            Car foundCar = carDB.getCarByVin(db, "JHG4536KFD");
            if (foundCar != null) {
                carId = foundCar.getId();
                System.out.println("Car found by VIN: " + foundCar);
            }

            // Fetching car by ID
            Car carById = carDB.getCarById(db, car.getId());
            if (carById != null) {
                System.out.println("Car found by ID: " + carById);
            }

            // Updating car data
            car.setMileage(45000);
            carDB.updateCar(db, car);
            System.out.println("Car updated: " + car);

            // Deleting the car
            //carDB.deleteCar(db, car.getId());
            //System.out.println("Car deleted.");

        } catch (SQLException e) {
            System.err.println("Error working with cars: " + e.getMessage());
        }

        // ** Working with Renter table **
        RenterDB renterDB = new RenterDB();
        Renter renter = new Renter("Ivanov", "Ivan", "AA123456", "B12345");

        try {
            // Adding a new renter
            renterDB.addRenter(db, renter);
            System.out.println("Renter added: " + renter);

            // Fetching all renters
            List<Renter> renters = renterDB.getAllRenters(db);
            System.out.println("All renters in the database:");
            for (Renter r : renters) {
                renterId = r.getId();
                System.out.println(r);
            }

            // Updating renter data
            renter.setDriverLicense("B98765");
            renterDB.updateRenter(db, renter);
            System.out.println("Renter updated: " + renter);

            // Deleting the renter
            //renterDB.deleteRenter(db, renter.getId());
            //System.out.println("Renter deleted.");

        } catch (SQLException e) {
            System.err.println("Error working with renters: " + e.getMessage());
        }

        // ** Working with Rental table **
        RentalDB rentalDB = new RentalDB();

        car.setId(carId);
        renter.setId(renterId);
        Rental rental = new Rental(car, renter, "Center", "Village", LocalDate.now(), LocalDate.now().plusDays(7), 500);

        try {
            // Adding a new rental
            rentalDB.addRental(db, rental);
            System.out.println("Rental added: " + rental);

            // Fetching all rentals
            List<Rental> rentals = rentalDB.getAllRentals(db);
            System.out.println("All rentals in the database:");
            for (Rental r : rentals) {
                System.out.println(r);
            }

            // Updating rental data
            rental.setPricePerDay(600);
            rentalDB.updateRental(db, rental);
            System.out.println("Rental updated: " + rental);

            // Deleting the rental
            rentalDB.deleteRental(db, rental.getId());
            System.out.println("Rental deleted.");

        } catch (SQLException e) {
            System.err.println("Error working with rentals: " + e.getMessage());
        }

        // Закриття підключення до бази та очищення таблиць
        try {
            if (db != null) {
                DatabaseManager.clearTables(db); // Очищення таблиць перед завершенням
                db.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the database connection: " + e.getMessage());
        }
    }
}
