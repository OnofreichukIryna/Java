package Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Клас представляє машину для оренди.
 * Містить інформацію про марку машини, VIN-код, номерний знак, дату випуску та пробіг.
 */
public class Car implements Comparable<Car> {
    private String brand;          // Марка машини
    private String vin;            // VIN-код
    private String licensePlate;   // Номерний знак
    private LocalDate releaseDate; // Дата випуску
    private int mileage;           // Пробіг

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

    /**
     * Реалізація методу порівняння для Comparable.
     * Порівнює машини за датою випуску.
     *
     * @param other об'єкт Car для порівняння
     * @return від'ємне число, якщо поточний об'єкт старший; додатнє, якщо новіший; 0, якщо дати однакові
     */
    @Override
    public int compareTo(Car other) {
        return this.releaseDate.compareTo(other.releaseDate);
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

    // Getter для дати випуску.
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
