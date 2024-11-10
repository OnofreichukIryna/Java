package org.example;

import Serializers.JsonEntitySerializer;
import Serializers.XmlEntitySerializer;
import Serializers.YamlEntitySerializer;
import org.example.Car;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Демонстрація випадків з некоректними даними
        demonstrateInvalidCases();

        // Демонстрація випадків з коректними даними
        demonstrateValidCases();
    }

    /**
     * Демонстрація некоректних даних і валідації з повідомленням про помилки.
     */
    public static void demonstrateInvalidCases() {
        List<Object> createdObjects = new ArrayList<>();

        try {
            // Спроба створення об'єкта Car з некоректними параметрами
            Car car = new Car.Builder()// Коротка назва (мін. 2 символи)
                    .setBrand("")          // Порожня модель
                    .setVin("efww45365435fdfdsfewf")         // неправильна довжина він коду
                    .setLicensePlate("sd")   // неправильна довжина номерного знаку
                    .setReleaseDate(LocalDate.of(2024, 12, 31))     // неправильна дата випуску (не може бути раніше за теперишнє число)
                    .setMileage(-200)    // не може бути від'ємним значенням
                    .build();
            createdObjects.add(car);
        } catch (IllegalArgumentException e) {
            System.out.println("Errors in Car creation: " + e.getMessage());
        }

        try {
            // Спроба створення об'єкта Renter з некоректними параметрами
            Renter renter = new Renter.Builder()
                    .setLastName("")
                    .setFirstName("")
                    .setDocument("123")           // Некоректний номер документа
                    .setDriverLicense("")         // Порожній номер водійських прав
                    .build();
            createdObjects.add(renter);
        } catch (IllegalArgumentException e) {
            System.out.println("Errors in Renter creation: " + e.getMessage());
        }

        try {
            // Спроба створення об'єкта Rental з некоректними параметрами
            Rental rental = new Rental.Builder()
                    .setCar(null)                     // Машина не може бути null
                    .setRenter(new Renter("John", "Doe", "AB123456", "DL123456"))
                    .setPickupLocation("")            // Порожнє місце забирання
                    .setDropoffLocation("Center")      // Коректне місце повернення
                    .setStartDate(LocalDate.of(2024, 12, 31))
                    .setEndDate(LocalDate.of(2024, 1, 1)) // Дата закінчення раніше дати початку
                    .setPricePerDay(0)                // Ціна за день має бути більше 0
                    .build();
            createdObjects.add(rental);
        } catch (IllegalArgumentException e) {
            System.out.println("Errors in Rental creation: " + e.getMessage());
        }

        // Перевірка, чи були успішно створені об'єкти
        if (createdObjects.isEmpty()) {
            System.out.println("No objects were created due to validation errors.\n");
        }
    }

    /**
     * Демонстрація коректних даних і успішного створення об'єктів.
     */
    public static void demonstrateValidCases() {
        List<Object> createdObjects = new ArrayList<>();

        try {
            // Створення об'єкта Car з коректними параметрами
            Car car = new Car.Builder()
                    .setBrand("Toyota")
                    .setVin("Y6D1103078XXXXXXX")
                    .setLicensePlate("CE1585EH")
                    .setReleaseDate(LocalDate.of(2018, 01, 01))
                    .setMileage(40000)
                    .build();
            createdObjects.add(car);
            System.out.println("Car created successfully: " + car);
        } catch (IllegalArgumentException e) {
            System.out.println("Errors in Car creation: " + e.getMessage());
        }

        try {
            // Створення об'єкта Renter з коректними параметрами
            Renter renter = new Renter.Builder()
                    .setLastName("Smith")
                    .setFirstName("John")
                    .setDocument("AB123456")
                    .setDriverLicense("DL789123")
                    .build();
            createdObjects.add(renter);
            System.out.println("Renter created successfully: " + renter);
        } catch (IllegalArgumentException e) {
            System.out.println("Errors in Renter creation: " + e.getMessage());
        }

        try {
            // Створення об'єкта Rental з коректними параметрами
            Rental rental = new Rental.Builder()
                    .setCar(new Car("Toyota", "Y6D1103078XXXXXXX", "CE1585EH", LocalDate.of(2018, 01, 01), 40000))
                    .setRenter(new Renter("Johnson", "Tom", "AB123789", "DL456789"))
                    .setPickupLocation("Airport")
                    .setDropoffLocation("Hotel")
                    .setStartDate(LocalDate.of(2024, 5, 1))
                    .setEndDate(LocalDate.of(2024, 5, 10))
                    .setPricePerDay(60)
                    .build();
            createdObjects.add(rental);
            System.out.println("Rental created successfully: " + rental);
        } catch (IllegalArgumentException e) {
            System.out.println("Errors in Rental creation: " + e.getMessage());
        }

        // Перевірка, чи були успішно створені об'єкти
        if (!createdObjects.isEmpty()) {
            System.out.println("Successfully created objects:");
            createdObjects.forEach(System.out::println);
        }
    }
}
