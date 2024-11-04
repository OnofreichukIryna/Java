package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Створюємо екземпляр класу Car (машина).
        Car car1 = new Car("Toyota", "JTDBR32E530129485", "AA1234BB", LocalDate.of(2015, 6, 10), 85000, 1000);
        Car car2 = new Car("Tesla", "1HGCM82633A004352", "AB5678CD", LocalDate.of(2020, 8, 15), 45000, 1500);

        // Виводимо інформацію про машини.
        System.out.println("Car info 1: " + car1);
        System.out.println("Car info 2: " + car2);

        // Перевіряємо рівність двох машин (очікується false, оскільки це різні машини).
        System.out.println("Are cars equal? " + car1.equals(car2));

        // Створюємо екземпляр класу Renter (орендар).
        Renter renter1 = new Renter("Onofreichuk", "Iryna", "Passport", "VK123456");

        // Виводимо інформацію про орендаря.
        System.out.println("Renter info: " + renter1);

        // Створюємо оренду (Rental) за допомогою паттерна Builder.
        Rental rental1 = new Rental.Builder()
                .setCar(car1)                                  // Призначаємо машину для оренди.
                .setRenter(renter1)                            // Призначаємо орендаря.
                .setPickupLocation("Kyiv")                     // Локація, де забирають машину.
                .setDropoffLocation("Lviv")                    // Локація, куди повернуть машину.
                .setStartDate(LocalDate.of(2024, 10, 20))      // Дата початку оренди.
                .setEndDate(LocalDate.of(2024, 10, 25))        // Дата закінчення оренди.                 // Ціна за день оренди.
                .build();                                      // Будуємо об'єкт оренди.

        // Виводимо інформацію про оренду.
        System.out.println("Rental info: " + rental1);

        // Обчислюємо загальну вартість оренди.
        System.out.println("Total rental price: " + rental1.calculateTotalPrice() + " UAH");

        // Демонстрація рівності об'єктів.
        Rental rental2 = new Rental.Builder()
                .setCar(car1)
                .setRenter(renter1)
                .setPickupLocation("Kyiv")
                .setDropoffLocation("Lviv")
                .setStartDate(LocalDate.of(2024, 10, 20))
                .setEndDate(LocalDate.of(2024, 10, 25))
                .build();

        // Перевірка рівності двох оренд (очікується true, оскільки всі параметри однакові).
        System.out.println("Are the two rentals equal? " + rental1.equals(rental2));

        // Виводимо хеш-коди об'єктів.
        System.out.println("Rental 1 hash code: " + rental1.hashCode());
        System.out.println("Rental 2 hash code: " + rental2.hashCode());
    }
}
