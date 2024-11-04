package org.example;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Клас представляє оренду машини.
 * Містить інформацію про машину, орендаря, місце забирання і повернення, дати оренди, та вартість.
 */
public class Rental {
    private Car car;               // Машина, що орендується
    private Renter renter;         // Орендар
    private String pickupLocation; // Місце забирання машини
    private String dropoffLocation;// Місце повернення машини
    private LocalDate startDate;   // Дата початку оренди
    private LocalDate endDate;     // Дата закінчення оренди
    private double totalPrice;    // Загальна ціна за оренду

    // Приватний конструктор для паттерну Builder
    private Rental(Builder builder) {
        this.car = builder.car;
        this.renter = builder.renter;
        this.pickupLocation = builder.pickupLocation;
        this.dropoffLocation = builder.dropoffLocation;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        if(car != null){
            totalPrice = calculateTotalPrice();
        }
    }

    /**
     * Метод обчислює загальну вартість оренди.
     * @return загальна ціна оренди
     */
    public double calculateTotalPrice() {
        return car.getPricePerDay() * (endDate.toEpochDay() - startDate.toEpochDay());
    }

    /**
     * Метод повертає строкове представлення оренди.
     * @return рядок з інформацією про оренду
     */
    @Override
    public String toString() {
        return "Rental{" +
                "car=" + car +
                ", renter=" + renter +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", total price=" + totalPrice +
                '}';
    }

    /**
     * Метод перевіряє рівність двох об'єктів типу Rental.
     * @param o об'єкт для порівняння
     * @return true, якщо об'єкти рівні; false — в іншому випадку
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Double.compare(rental.totalPrice, totalPrice) == 0 &&
                Objects.equals(car, rental.car) &&
                Objects.equals(renter, rental.renter) &&
                Objects.equals(pickupLocation, rental.pickupLocation) &&
                Objects.equals(dropoffLocation, rental.dropoffLocation) &&
                Objects.equals(startDate, rental.startDate) &&
                Objects.equals(endDate, rental.endDate);
    }

    /**
     * Метод повертає хеш-код об'єкта.
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(car, renter, pickupLocation, dropoffLocation, startDate, endDate, totalPrice);
    }

    /**
     * Паттерн Builder для класу Rental.
     */
    public static class Builder {
        private Car car;
        private Renter renter;
        private String pickupLocation;
        private String dropoffLocation;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder setCar(Car car) {
            this.car = car;
            return this;
        }

        public Builder setRenter(Renter renter) {
            this.renter = renter;
            return this;
        }

        public Builder setPickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
            return this;
        }

        public Builder setDropoffLocation(String dropoffLocation) {
            this.dropoffLocation = dropoffLocation;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Rental build() {
            return new Rental(this);
        }
    }
}
