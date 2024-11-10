package Models;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з таблицею Car у базі даних.
 */
public class CarDB {

    /**
     * Метод для додавання нового автомобіля в базу даних.
     * @param connection Підключення до бази даних.
     * @param car Екземпляр класу Car для збереження.
     * @return Кількість рядків, на які вплинув запит.
     */
    public int addCar(Connection connection, Car car) throws SQLException {
        String query = "INSERT INTO Car (brand, vin, license_plate, release_date, mileage) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getVin());
            statement.setString(3, car.getLicensePlate());
            statement.setDate(4, Date.valueOf(car.getReleaseDate()));
            statement.setInt(5, car.getMileage());
            return statement.executeUpdate();
        }
    }

    /**
     * Метод для отримання всіх автомобілів з бази даних.
     * @param connection Підключення до бази даних.
     * @return Список автомобілів.
     */
    public List<Car> getAllCars(Connection connection) throws SQLException {
        String query = "SELECT id, brand, vin, license_plate, release_date, mileage FROM Car";
        List<Car> cars = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setVin(resultSet.getString("vin"));
                car.setLicensePlate(resultSet.getString("license_plate"));
                car.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
                car.setMileage(resultSet.getInt("mileage"));
                cars.add(car);
            }
        }
        return cars;
    }

    /**
     * Метод для оновлення автомобіля в базі даних.
     * @param connection Підключення до бази даних.
     * @param car Екземпляр класу Car з оновленими даними.
     * @return Кількість рядків, на які вплинув запит.
     */
    public int updateCar(Connection connection, Car car) throws SQLException {
        String query = "UPDATE Car SET brand = ?, vin = ?, license_plate = ?, release_date = ?, mileage = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getVin());
            statement.setString(3, car.getLicensePlate());
            statement.setDate(4, Date.valueOf(car.getReleaseDate()));
            statement.setInt(5, car.getMileage());
            statement.setInt(6, car.getId());
            return statement.executeUpdate();
        }
    }

    /**
     * Метод для видалення автомобіля з бази даних за його ідентифікатором.
     * @param connection Підключення до бази даних.
     * @param carId Ідентифікатор автомобіля для видалення.
     * @return Кількість рядків, на які вплинув запит.
     */
    public int deleteCar(Connection connection, int carId) throws SQLException {
        String query = "DELETE FROM Car WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, carId);
            return statement.executeUpdate();
        }
    }

    /**
     * Метод для отримання автомобіля за його VIN-кодом.
     * @param connection Підключення до бази даних.
     * @param vin VIN-код автомобіля.
     * @return Екземпляр класу Car або null, якщо автомобіль не знайдено.
     */
    public Car getCarByVin(Connection connection, String vin) throws SQLException {
        String query = "SELECT id, brand, vin, license_plate, release_date, mileage FROM Car WHERE vin = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, vin);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("id"));
                    car.setBrand(resultSet.getString("brand"));
                    car.setVin(resultSet.getString("vin"));
                    car.setLicensePlate(resultSet.getString("license_plate"));
                    car.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
                    car.setMileage(resultSet.getInt("mileage"));
                    return car;
                }
            }
        }
        return null;
    }

    public Car getCarById(Connection connection, int carId) throws SQLException {
        String query = "SELECT id, brand, vin, license_plate, release_date, mileage FROM Car WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, carId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("id"));
                    car.setBrand(resultSet.getString("brand"));
                    car.setVin(resultSet.getString("vin"));
                    car.setLicensePlate(resultSet.getString("license_plate"));
                    car.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
                    car.setMileage(resultSet.getInt("mileage"));
                    return car;
                }
            }
        }
        return null;
    }
}
