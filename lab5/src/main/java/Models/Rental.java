package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Клас представляє оренду машини.
 * Містить інформацію про машину, орендаря, місце забирання і повернення, дати оренди, та вартість.
 */
public class Rental {
    private int id;
    private Car car;               // Машина, що орендується
    private Renter renter;         // Орендар
    private String pickupLocation; // Місце забирання машини
    private String dropoffLocation;// Місце повернення машини
    private LocalDate startDate;   // Дата початку оренди
    private LocalDate endDate;     // Дата закінчення оренди
    private double pricePerDay;    // Ціна за день оренди

    public Rental() {}

    public Rental(Car car, Renter renter, String pickupLocation, String dropoffLocation, LocalDate startDate, LocalDate endDate, double pricePerDay){
        this.car = car;
        this.renter = renter;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pricePerDay = pricePerDay;
    }

    // Приватний конструктор для паттерну Builder
    private Rental(Builder builder) {
        this.car = builder.car;
        this.renter = builder.renter;
        this.pickupLocation = builder.pickupLocation;
        this.dropoffLocation = builder.dropoffLocation;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.pricePerDay = builder.pricePerDay;
    }

    /**
     * Метод обчислює загальну вартість оренди.
     * @return загальна ціна оренди
     */
    public double calculateTotalPrice() {
        return pricePerDay * (endDate.toEpochDay() - startDate.toEpochDay());
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
                ", pricePerDay=" + pricePerDay +
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
        return Double.compare(rental.pricePerDay, pricePerDay) == 0 &&
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
        return Objects.hash(car, renter, pickupLocation, dropoffLocation, startDate, endDate, pricePerDay);
    }

    // Сервісний метод 1: Сортування оренд за ціною за день.
    public static List<Rental> sortByPricePerDay(List<Rental> rentals) {
        return rentals.stream()
                .sorted(Comparator.comparingDouble(Rental::getPricePerDay))
                .collect(Collectors.toList());
    }

    // Сервісний метод 2: Фільтрація оренд за місцем забирання.
    public static List<Rental> filterByPickupLocation(List<Rental> rentals, String location) {
        return rentals.stream()
                .filter(rental -> rental.getPickupLocation().equals(location))
                .collect(Collectors.toList());
    }

    // Сервісний метод 3: Пошук оренди за датою початку.
    public static Rental findByStartDate(List<Rental> rentals, LocalDate startDate) {
        return rentals.stream()
                .filter(rental -> rental.getStartDate().equals(startDate))
                .findFirst()
                .orElse(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
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
        private double pricePerDay;
        private final List<String> validationErrors = new ArrayList<>();

        public Builder setCar(Car car) {
            if (car == null) {
                validationErrors.add("Car cannot be null.");
            } else {
                this.car = car;
            }
            return this;
        }

        public Builder setRenter(Renter renter) {
            if (renter == null) {
                validationErrors.add("Renter cannot be null.");
            } else {
                this.renter = renter;
            }
            return this;
        }

        public Builder setPickupLocation(String pickupLocation) {
            if (pickupLocation == null || pickupLocation.isEmpty()) {
                validationErrors.add("The pickup location cannot be empty.");
            } else {
                this.pickupLocation = pickupLocation;
            }
            return this;
        }

        public Builder setDropoffLocation(String dropoffLocation) {
            if (dropoffLocation == null || dropoffLocation.isEmpty()) {
                validationErrors.add("The return location cannot be empty.");
            } else {
                this.dropoffLocation = dropoffLocation;
            }
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            if (startDate == null) {
                validationErrors.add("The start date cannot be null.");
            } else if (endDate != null && startDate.isAfter(endDate)) {
                validationErrors.add("The start date cannot be later than the end date.");
            } else {
                this.startDate = startDate;
            }
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            if (endDate == null) {
                validationErrors.add("The end date cannot be null.");
            } else if (startDate != null && endDate.isBefore(startDate)) {
                validationErrors.add("The end date cannot be earlier than the start date.");
            } else {
                this.endDate = endDate;
            }
            return this;
        }

        public Builder setPricePerDay(double pricePerDay) {
            if (pricePerDay <= 0) {
                validationErrors.add("Price per day must be greater than 0.");
            } else {
                this.pricePerDay = pricePerDay;
            }
            return this;
        }

        public Rental build() {
            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException("Validation errors: " + String.join("; ", validationErrors));
            }
            return new Rental(this);
        }
    }

}
