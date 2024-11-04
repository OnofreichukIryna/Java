package org.example;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Клас представляє машину для оренди.
 * Містить інформацію про марку машини, VIN-код, номерний знак, дату випуску та пробіг.
 */
public class Car {
    private String brand;          // Марка машини
    private String vin;            // VIN-код
    private String licensePlate;   // Номерний знак
    private LocalDate releaseDate; // Дата випуску
    private int mileage;           // Пробіг
    private double pricePerDay;    // Ціна за день оренди

    /**
     * Конструктор для створення об'єкта машини.
     *
     * @param brand марка машини
     * @param vin VIN-код машини
     * @param licensePlate номерний знак машини
     * @param releaseDate дата випуску машини
     * @param mileage пробіг машини
     * @param pricePerDay ціна за день оренди
     */
    public Car(String brand, String vin, String licensePlate, LocalDate releaseDate, int mileage, double pricePerDay) {
        this.brand = brand;
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.releaseDate = releaseDate;
        this.mileage = mileage;
        this.pricePerDay = pricePerDay;
    }

    /**
     * Метод повертає строкове представлення машини.
     * @return рядок з інформацією про машину
     */
    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", vin='" + vin + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", releaseDate=" + releaseDate +
                ", mileage=" + mileage +
                ", price per day=" + pricePerDay +
                '}';
    }

    /**
     * Метод перевіряє рівність двох об'єктів типу Car.
     * @param o об'єкт для порівняння
     * @return true, якщо об'єкти рівні; false — в іншому випадку
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return mileage == car.mileage &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(vin, car.vin) &&
                Objects.equals(licensePlate, car.licensePlate) &&
                Objects.equals(releaseDate, car.releaseDate) &&
                Objects.equals(pricePerDay, car.pricePerDay);
    }

    /**
     * Метод повертає хеш-код об'єкта.
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(brand, vin, licensePlate, releaseDate, mileage, pricePerDay);
    }

    public double getPricePerDay(){
        return pricePerDay;
    }
}
