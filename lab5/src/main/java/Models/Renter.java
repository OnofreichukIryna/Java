package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Клас представляє орендаря автомобіля.
 * Містить інформацію про особу орендаря: прізвище, ім'я, документ, що підтверджує особу, і водійські права.
 */
public class Renter {
    private int id;
    private String lastName;       // Прізвище орендаря
    private String firstName;      // Ім'я орендаря
    private String document;       // Документ, що підтверджує особу (наприклад, паспорт)
    private String driverLicense;  // Водійські права

    public Renter() {}

    /**
     * Конструктор для створення орендаря.
     *
     * @param lastName прізвище орендаря
     * @param firstName ім'я орендаря
     * @param document документ, що підтверджує особу
     * @param driverLicense водійські права
     */
    public Renter(String lastName, String firstName, String document, String driverLicense) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.document = document;
        this.driverLicense = driverLicense;
    }

    public Renter(Builder builder){
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.document = builder.document;
        this.driverLicense = builder.driverLicense;
    }

    /**
     * Метод повертає строкове представлення орендаря.
     * @return рядок з інформацією про орендаря
     */
    @Override
    public String toString() {
        return "Renter{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", document='" + document + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                '}';
    }

    /**
     * Метод перевіряє рівність двох об'єктів типу Renter.
     * @param o об'єкт для порівняння
     * @return true, якщо об'єкти рівні; false — в іншому випадку
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renter renter = (Renter) o;
        return Objects.equals(lastName, renter.lastName) &&
                Objects.equals(firstName, renter.firstName) &&
                Objects.equals(document, renter.document) &&
                Objects.equals(driverLicense, renter.driverLicense);
    }

    /**
     * Метод повертає хеш-код об'єкта.
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, document, driverLicense);
    }

    // Сервісний метод 1: Сортування орендарів за прізвищем.
    public static List<Renter> sortByLastName(List<Renter> renters) {
        return renters.stream()
                .sorted(Comparator.comparing(Renter::getLastName))
                .collect(Collectors.toList());
    }

    // Сервісний метод 2: Фільтрація орендарів за іменем.
    public static List<Renter> filterByFirstName(List<Renter> renters, String firstName) {
        return renters.stream()
                .filter(renter -> renter.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
    }

    // Сервісний метод 3: Пошук орендаря за документом.
    public static Renter findByDocument(List<Renter> renters, String document) {
        return renters.stream()
                .filter(renter -> renter.getDocument().equals(document))
                .findFirst()
                .orElse(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public static class Builder {
        private String lastName;
        private String firstName;
        private String document;
        private String driverLicense;
        private final List<String> validationErrors = new ArrayList<>();

        public Builder setLastName(String lastName) {
            if (lastName == null || lastName.isEmpty()) {
                validationErrors.add("The last name cannot be empty.");
            } else {
                this.lastName = lastName;
            }
            return this;
        }

        public Builder setFirstName(String firstName) {
            if (firstName == null || firstName.isEmpty()) {
                validationErrors.add("The name cannot be empty.");
            } else {
                this.firstName = firstName;
            }
            return this;
        }

        public Builder setDocument(String document) {
            if (document == null || !document.matches("[A-Z]{2}[0-9]{6}")) { // Приклад: формат документу AA123456
                validationErrors.add("The document is invalid: " + document);
            } else {
                this.document = document;
            }
            return this;
        }

        public Builder setDriverLicense(String driverLicense) {
            if (driverLicense == null || !driverLicense.matches("[A-Z0-9]{5,10}")) { // Приклад: від 5 до 10 символів
                validationErrors.add("Driver's license is invalid: " + driverLicense);
            } else {
                this.driverLicense = driverLicense;
            }
            return this;
        }

        public Renter build() {
            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException("Validation errors: " + String.join("; ", validationErrors));
            }
            return new Renter(this);
        }
    }

}
