package Models;

import Serializers.JsonEntitySerializer;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з таблицею Rental у базі даних.
 */
public class RentalDB {

    private final JsonEntitySerializer<Rental> jsonSerializer = new JsonEntitySerializer<>(); // Сериализатор JSON для роботи з Rental

    /**
     * Метод для додавання нового запису оренди в базу даних.
     * @param connection Підключення до бази даних.
     * @param rental Екземпляр класу Rental для збереження.
     * @return Кількість рядків, на які вплинув запит.
     */
    public int addRental(Connection connection, Rental rental) throws SQLException {
        String query = "INSERT INTO Rental (car_id, renter_id, pickup_location, dropoff_location, start_date, end_date, price_per_day) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rental.getCar().getId());
            statement.setInt(2, rental.getRenter().getId());
            statement.setString(3, rental.getPickupLocation());
            statement.setString(4, rental.getDropoffLocation());
            statement.setDate(5, Date.valueOf(rental.getStartDate()));
            statement.setDate(6, Date.valueOf(rental.getEndDate()));
            statement.setDouble(7, rental.getPricePerDay());

            return statement.executeUpdate();
        }
    }

    /**
     * Метод для отримання всіх оренд з бази даних.
     * @param connection Підключення до бази даних.
     * @return Список оренд.
     */
    public List<Rental> getAllRentals(Connection connection) throws SQLException {
        String query = "SELECT * FROM Rental";
        List<Rental> rentals = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Rental rental = mapResultSetToRental(connection, resultSet);
                rentals.add(rental);
            }
        }
        return rentals;
    }

    /**
     * Метод для оновлення запису оренди в базі даних.
     * @param connection Підключення до бази даних.
     * @param rental Екземпляр класу Rental з оновленими даними.
     * @return Кількість рядків, на які вплинув запит.
     */
    public int updateRental(Connection connection, Rental rental) throws SQLException {
        String query = "UPDATE Rental SET car_id = ?, renter_id = ?, pickup_location = ?, dropoff_location = ?, start_date = ?, end_date = ?, price_per_day = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rental.getCar().getId());
            statement.setInt(2, rental.getRenter().getId());
            statement.setString(3, rental.getPickupLocation());
            statement.setString(4, rental.getDropoffLocation());
            statement.setDate(5, Date.valueOf(rental.getStartDate()));
            statement.setDate(6, Date.valueOf(rental.getEndDate()));
            statement.setDouble(7, rental.getPricePerDay());
            statement.setInt(8, rental.getCar().getId());

            return statement.executeUpdate();
        }
    }

    /**
     * Метод для видалення запису оренди з бази даних за його ідентифікатором.
     * @param connection Підключення до бази даних.
     * @param rentalId Ідентифікатор оренди для видалення.
     * @return Кількість рядків, на які вплинув запит.
     */
    public int deleteRental(Connection connection, int rentalId) throws SQLException {
        String query = "DELETE FROM Rental WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rentalId);
            return statement.executeUpdate();
        }
    }

    /**
     * Метод для перетворення результату запиту в об'єкт Rental.
     * @param connection Підключення до бази даних.
     * @param resultSet Результат запиту.
     * @return Об'єкт Rental.
     */
    private Rental mapResultSetToRental(Connection connection, ResultSet resultSet) throws SQLException {
        CarDB carDB = new CarDB();
        RenterDB renterDB = new RenterDB();

        Car car = carDB.getCarById(connection, resultSet.getInt("car_id"));
        Renter renter = renterDB.getRenterById(connection, resultSet.getInt("renter_id"));

        return new Rental.Builder()
                .setCar(car)
                .setRenter(renter)
                .setPickupLocation(resultSet.getString("pickup_location"))
                .setDropoffLocation(resultSet.getString("dropoff_location"))
                .setStartDate(resultSet.getDate("start_date").toLocalDate())
                .setEndDate(resultSet.getDate("end_date").toLocalDate())
                .setPricePerDay(resultSet.getDouble("price_per_day"))
                .build();
    }
}
