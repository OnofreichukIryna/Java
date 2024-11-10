package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Клас представляє машину для оренди.
 * Містить інформацію про марку машини, VIN-код, номерний знак, дату випуску та пробіг.
 */
public class Car {
    private String brand;          // Марка машини
    private String vin;            // VIN-код
    private String licensePlate;   // Номерний знак
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Формат дати
    private LocalDate releaseDate; // Дата випуску
    private int mileage;           // Пробіг

    public Car() {
    }

    /**
     * Конструктор для створення об'єкта машини.
     *
     * @param brand марка машини
     * @param vin VIN-код машини
     * @param licensePlate номерний знак машини
     * @param releaseDate дата випуску машини
     * @param mileage пробіг машини
     */
    public Car(String brand, String vin, String licensePlate, LocalDate releaseDate, int mileage) {
        this.brand = brand;
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.releaseDate = releaseDate;
        this.mileage = mileage;
    }

    public Car(Builder builder){
        this.brand = builder.brand;
        this.vin = builder.vin;
        this.licensePlate = builder.licensePlate;
        this.releaseDate = builder.releaseDate;
        this.mileage = builder.mileage;
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
                Objects.equals(releaseDate, car.releaseDate);
    }

    /**
     * Метод повертає хеш-код об'єкта.
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(brand, vin, licensePlate, releaseDate, mileage);
    }

    // Сервісний метод 1: Сортування машин за маркою.
    public static List<Car> sortByBrand(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getBrand))
                .collect(Collectors.toList());
    }

    // Сервісний метод 2: Фільтрація машин за пробігом.
    public static List<Car> filterByMileage(List<Car> cars, int mileageThreshold) {
        return cars.stream()
                .filter(car -> car.getMileage() < mileageThreshold)
                .collect(Collectors.toList());
    }

    // Сервісний метод 3: Пошук машини за VIN-кодом.
    public static Car findByVin(List<Car> cars, String vin) {
        return cars.stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst()
                .orElse(null);
    }

    // Getter для марки.
    public String getBrand() {
        return brand;
    }

    // Getter для пробігу.
    public int getMileage() {
        return mileage;
    }

    // Getter для VIN-коду.
    public String getVin() {
        return vin;
    }

    public String getLicensePlate() {return licensePlate;}

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public static class Builder {
        private String brand;
        private String vin;
        private String licensePlate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate releaseDate;
        private int mileage;
        private final List<String> validationErrors = new ArrayList<>();

        public Builder setBrand(String brand) {
            if (brand == null || brand.isEmpty()) {
                validationErrors.add("The car brand cannot be empty.");
            } else {
                this.brand = brand;
            }
            return this;
        }

        public Builder setVin(String vin) {
            if (vin == null || !vin.matches("[A-HJ-NPR-Z0-9]{17}")) { // VIN має містити 17 символів без I, O, Q
                validationErrors.add("VIN code is invalid: " + vin);
            } else {
                this.vin = vin;
            }
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            if (licensePlate == null || !licensePlate.matches("[A-Z0-9]{1,8}")) { // Приклад: обмеження до 8 символів
                validationErrors.add("License plate is invalid: " + licensePlate);
            } else {
                this.licensePlate = licensePlate;
            }
            return this;
        }

        public Builder setReleaseDate(LocalDate releaseDate) {
            if (releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
                validationErrors.add("The release date is not valid (cannot be earlier than today): " + releaseDate);
            } else {
                this.releaseDate = releaseDate;
            }
            return this;
        }

        public Builder setMileage(int mileage) {
            if (mileage < 0) {
                validationErrors.add("Mileage cannot be negative: " + mileage);
            } else {
                this.mileage = mileage;
            }
            return this;
        }

        public Car build() {
            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException("Validation errors: " + String.join("; ", validationErrors));
            }
            return new Car(this);
        }
    }

}
