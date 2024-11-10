package Models;

import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Клас представляє орендаря автомобіля.
 * Містить інформацію про особу орендаря: прізвище, ім'я, документ, що підтверджує особу, і водійські права.
 */
public class Renter implements Comparable<Renter> {
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

    /**
     * Реалізація методу порівняння для Comparable.
     * Порівнює орендарів за прізвищем.
     *
     * @param other об'єкт Renter для порівняння
     * @return від'ємне число, якщо поточний об'єкт має прізвище, яке йде перед іншим; додатнє, якщо навпаки; 0, якщо однакові
     */
    @Override
    public int compareTo(Renter other) {
        return this.lastName.compareTo(other.lastName);
    }

    // Getter для прізвища.
    public String getLastName() {
        return lastName;
    }

    // Getter для імені.
    public String getFirstName() {
        return firstName;
    }

    // Getter для документу.
    public String getDocument() {
        return document;
    }

    // Getter для водійських прав.
    public String getDriverLicense() {
        return driverLicense;
    }
}
