package org.example;

import Models.Car;
import Models.Rental;
import Models.Renter;
import Services.CarService;
import Services.RentalService;
import Services.RenterService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Демонстрація методів CarService
        CarService carService = new CarService();
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "VIN12345", "ABC123", LocalDate.of(2015, 5, 10), 50000));
        cars.add(new Car("BMW", "VIN67890", "XYZ789", LocalDate.of(2018, 7, 15), 30000));
        cars.add(new Car("Audi", "VIN54321", "DEF456", LocalDate.of(2012, 3, 5), 70000));

        // Сортування машин за датою випуску
        System.out.println("Sorted cars by release date:");
        carService.sortCarsByReleaseDate(cars).forEach(System.out::println);

        // Фільтрація машин за пробігом
        System.out.println("\nFiltered cars with mileage over 40000:");
        carService.filterCarsByMileage(cars, 40000).forEach(System.out::println);

        // Сортування машин за маркою
        System.out.println("\nSorted cars by brand:");
        carService.sortCarsByBrand(cars).forEach(System.out::println);


        // Демонстрація методів RentalService
        System.out.println("\n\n");
        RentalService rentalService = new RentalService();
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental.Builder().setCar(cars.get(0)).setRenter(new Renter("Smith", "John", "Passport123", "DL12345"))
                .setPickupLocation("New York").setDropoffLocation("Los Angeles")
                .setStartDate(LocalDate.of(2024, 1, 10)).setEndDate(LocalDate.of(2024, 1, 20)).setPricePerDay(50).build());
        rentals.add(new Rental.Builder().setCar(cars.get(1)).setRenter(new Renter("Doe", "Jane", "Passport456", "DL67890"))
                .setPickupLocation("San Francisco").setDropoffLocation("Seattle")
                .setStartDate(LocalDate.of(2023, 5, 5)).setEndDate(LocalDate.of(2023, 5, 15)).setPricePerDay(60).build());

        // Сортування оренд за датою початку
        System.out.println("Sorted rentals by start date:");
        rentalService.sortRentalsByStartDate(rentals).forEach(System.out::println);

        // Фільтрація оренд за ціною за день
        System.out.println("\nFiltered rentals with price per day over 55:");
        rentalService.filterRentalsByPricePerDay(rentals, 55).forEach(System.out::println);

        // Сортування оренд за місцем забирання
        System.out.println("\nSorted rentals by pickup location:");
        rentalService.sortRentalsByPickupLocation(rentals).forEach(System.out::println);


        // Демонстрація методів RenterService
        System.out.println("\n\n");
        RenterService renterService = new RenterService();
        List<Renter> renters = new ArrayList<>();
        renters.add(new Renter("Smith", "John", "Passport123", "DL12345"));
        renters.add(new Renter("Doe", "Jane", "Passport456", "DL67890"));
        renters.add(new Renter("Brown", "Michael", "Passport789", ""));

        // Сортування орендарів за прізвищем
        System.out.println("Sorted renters by last name:");
        renterService.sortRentersByLastName(renters).forEach(System.out::println);

        // Фільтрація орендарів з водійськими правами
        System.out.println("\nFiltered renters with driver licenses:");
        renterService.filterRentersWithDriverLicense(renters).forEach(System.out::println);

        // Сортування орендарів за ім'ям
        System.out.println("\nSorted renters by first name:");
        renterService.sortRentersByFirstName(renters).forEach(System.out::println);
    }
}
