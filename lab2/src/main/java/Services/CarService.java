package Services;

import Models.Car;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервісний клас для роботи з колекціями об'єктів Car.
 */
public class CarService {

    /**
     * Метод для сортування машин за датою випуску (використовує Comparable).
     *
     * @param cars список машин для сортування
     * @return відсортований список машин за датою випуску
     */
    public List<Car> sortCarsByReleaseDate(List<Car> cars) {
        return cars.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Метод для отримання списку машин з пробігом більшим за вказане значення.
     *
     * @param cars список машин для фільтрації
     * @param mileageThreshold мінімальний пробіг
     * @return відфільтрований список машин з пробігом більшим за threshold
     */
    public List<Car> filterCarsByMileage(List<Car> cars, int mileageThreshold) {
        return cars.stream()
                .filter(car -> car.getMileage() > mileageThreshold)
                .collect(Collectors.toList());
    }

    /**
     * Метод для сортування машин за маркою (використовує Comparator).
     *
     * @param cars список машин для сортування
     * @return відсортований список машин за маркою
     */
    public List<Car> sortCarsByBrand(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getBrand))
                .collect(Collectors.toList());
    }
}
