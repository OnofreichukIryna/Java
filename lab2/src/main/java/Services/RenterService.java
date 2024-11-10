package Services;

import Models.Renter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервісний клас для роботи з колекціями об'єктів Renter.
 */
public class RenterService {

    /**
     * Метод для сортування орендарів за прізвищем (використовує Comparable).
     *
     * @param renters список орендарів для сортування
     * @return відсортований список орендарів за прізвищем
     */
    public List<Renter> sortRentersByLastName(List<Renter> renters) {
        return renters.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Метод для фільтрації орендарів, що мають водійські права.
     *
     * @param renters список орендарів для фільтрації
     * @return відфільтрований список орендарів, що мають водійські права
     */
    public List<Renter> filterRentersWithDriverLicense(List<Renter> renters) {
        return renters.stream()
                .filter(renter -> renter.getDriverLicense() != null && !renter.getDriverLicense().isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Метод для сортування орендарів за ім'ям (використовує Comparator).
     *
     * @param renters список орендарів для сортування
     * @return відсортований список орендарів за ім'ям
     */
    public List<Renter> sortRentersByFirstName(List<Renter> renters) {
        return renters.stream()
                .sorted(Comparator.comparing(Renter::getFirstName))
                .collect(Collectors.toList());
    }
}
