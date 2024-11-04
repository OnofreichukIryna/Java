package org.example;

import java.util.Objects;

/**
 * Клас представляє орендаря автомобіля.
 * Містить інформацію про особу орендаря: прізвище, ім'я, документ, що підтверджує особу, і водійські права.
 */
public class Renter {
    private String lastName;       // Прізвище орендаря
    private String firstName;      // Ім'я орендаря
    private String document;       // Документ, що підтверджує особу (наприклад, паспорт)
    private String driverLicense;  // Водійські права

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

    /**
     * Метод повертає строкове представлення орендаря.
     * @return рядок з інформацією про орендаря
     */
    @Override
    public String toString() {
        return "Renter{" +
                "lastName='" + lastName + '\'' +
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
}
