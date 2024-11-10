package Services;

import Models.Rental;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервісний клас для роботи з колекціями об'єктів Rental.
 */
public class RentalService {

    /**
     * Метод для сортування оренд за датою початку оренди (використовує Comparable).
     *
     * @param rentals список оренд для сортування
     * @return відсортований список оренд за датою початку
     */
    public List<Rental> sortRentalsByStartDate(List<Rental> rentals) {
        return rentals.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Метод для фільтрації оренд за ціною, більшими за задану ціну.
     *
     * @param rentals список оренд для фільтрації
     * @param minPrice мінімальна ціна за день
     * @return відфільтрований список оренд з ціною за день більшою за minPrice
     */
    public List<Rental> filterRentalsByPricePerDay(List<Rental> rentals, double minPrice) {
        return rentals.stream()
                .filter(rental -> rental.getPricePerDay() > minPrice)
                .collect(Collectors.toList());
    }

    /**
     * Метод для сортування оренд за місцем забирання (використовує Comparator).
     *
     * @param rentals список оренд для сортування
     * @return відсортований список оренд за місцем забирання
     */
    public List<Rental> sortRentalsByPickupLocation(List<Rental> rentals) {
        return rentals.stream()
                .sorted(Comparator.comparing(Rental::getPickupLocation))
                .collect(Collectors.toList());
    }
}
