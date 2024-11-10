package Models;

import Models.Renter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з базою даних для моделі Renter.
 */
public class RenterDB {

    /**
     * Метод для додавання нового орендаря в базу даних.
     *
     * @param connection підключення до бази даних
     * @param renter     орендар для додавання
     * @throws SQLException у випадку помилок при виконанні запиту
     */
    public void addRenter(Connection connection, Renter renter) throws SQLException {
        String query = "INSERT INTO Renter (last_name, first_name, document, driver_license) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, renter.getLastName());
            statement.setString(2, renter.getFirstName());
            statement.setString(3, renter.getDocument());
            statement.setString(4, renter.getDriverLicense());
            statement.executeUpdate();
        }
    }

    /**
     * Метод для отримання списку всіх орендарів з бази даних.
     *
     * @param connection підключення до бази даних
     * @return список орендарів
     * @throws SQLException у випадку помилок при виконанні запиту
     */
    public List<Renter> getAllRenters(Connection connection) throws SQLException {
        String query = "SELECT id, last_name, first_name, document, driver_license FROM Renter";
        List<Renter> renters = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Renter renter = new Renter();
                renter.setId(resultSet.getInt("id"));
                renter.setLastName(resultSet.getString("last_name"));
                renter.setFirstName(resultSet.getString("first_name"));
                renter.setDocument(resultSet.getString("document"));
                renter.setDriverLicense(resultSet.getString("driver_license"));
                renters.add(renter);
            }
        }
        return renters;
    }

    /**
     * Метод для отримання орендаря за його документом.
     *
     * @param connection підключення до бази даних
     * @param document   документ орендаря
     * @return орендар, якщо знайдено, або null
     * @throws SQLException у випадку помилок при виконанні запиту
     */
    public Renter getRenterByDocument(Connection connection, String document) throws SQLException {
        String query = "SELECT id, last_name, first_name, document, driver_license FROM Renter WHERE document = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, document);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Renter renter = new Renter();
                    renter.setId(resultSet.getInt("id"));
                    renter.setLastName(resultSet.getString("last_name"));
                    renter.setFirstName(resultSet.getString("first_name"));
                    renter.setDocument(resultSet.getString("document"));
                    renter.setDriverLicense(resultSet.getString("driver_license"));
                    return renter;
                }
            }
        }
        return null;
    }

    /**
     * Метод для отримання орендаря за його id.
     *
     * @param connection підключення до бази даних
     * @param renterId   ідентифікатор орендаря
     * @return орендар, якщо знайдено, або null
     * @throws SQLException у випадку помилок при виконанні запиту
     */
    public Renter getRenterById(Connection connection, int renterId) throws SQLException {
        String query = "SELECT id, last_name, first_name, document, driver_license FROM Renter WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, renterId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Renter renter = new Renter();
                    renter.setId(resultSet.getInt("id"));
                    renter.setLastName(resultSet.getString("last_name"));
                    renter.setFirstName(resultSet.getString("first_name"));
                    renter.setDocument(resultSet.getString("document"));
                    renter.setDriverLicense(resultSet.getString("driver_license"));
                    return renter;
                }
            }
        }
        return null;
    }

    /**
     * Метод для оновлення інформації про орендаря.
     *
     * @param connection підключення до бази даних
     * @param renter     орендар з оновленими даними
     * @throws SQLException у випадку помилок при виконанні запиту
     */
    public void updateRenter(Connection connection, Renter renter) throws SQLException {
        String query = "UPDATE Renter SET last_name = ?, first_name = ?, document = ?, driver_license = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, renter.getLastName());
            statement.setString(2, renter.getFirstName());
            statement.setString(3, renter.getDocument());
            statement.setString(4, renter.getDriverLicense());
            statement.setInt(5, renter.getId());
            statement.executeUpdate();
        }
    }

    /**
     * Метод для видалення орендаря за id.
     *
     * @param connection підключення до бази даних
     * @param renterId   ідентифікатор орендаря для видалення
     * @throws SQLException у випадку помилок при виконанні запиту
     */
    public void deleteRenter(Connection connection, int renterId) throws SQLException {
        String query = "DELETE FROM Renter WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, renterId);
            statement.executeUpdate();
        }
    }
}
