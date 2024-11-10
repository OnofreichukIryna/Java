package DB;

import Models.Car;
import Models.Rental;
import Models.Renter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для управління базою даних, що містить методи підключення, створення таблиць та виконання CRUD операцій.
 */
public class DatabaseManager {
    private static final String URL = "jdbc:h2:file:./testdb"; // Підключення до бази в пам'яті
    private static final String USER = "sa"; // Стандартний користувач H2
    private static final String PASSWORD = ""; // Пароль порожній за замовчуванням

    /**
     * Метод для підключення до бази даних.
     * @return об'єкт Connection, що представляє підключення до бази даних.
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Метод для створення таблиць.
     */
    public static void createTables() {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            // Створення таблиці Car
            String createCarTable = "CREATE TABLE IF NOT EXISTS Car (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "brand VARCHAR(50) NOT NULL," +
                    "vin VARCHAR(17) UNIQUE NOT NULL," +
                    "license_plate VARCHAR(8) NOT NULL," +
                    "release_date DATE NOT NULL," +
                    "mileage INT NOT NULL" +
                    ")";
            statement.executeUpdate(createCarTable);

            // Створення таблиці Renter
            String createRenterTable = "CREATE TABLE IF NOT EXISTS Renter (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "last_name VARCHAR(50) NOT NULL," +
                    "first_name VARCHAR(50) NOT NULL," +
                    "document VARCHAR(8) UNIQUE NOT NULL," +
                    "driver_license VARCHAR(10) NOT NULL" +
                    ")";
            statement.executeUpdate(createRenterTable);

            // Створення таблиці Rental
            String createRentalTable = "CREATE TABLE IF NOT EXISTS Rental (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "car_id INT NOT NULL," +
                    "renter_id INT NOT NULL," +
                    "pickup_location VARCHAR(100) NOT NULL," +
                    "dropoff_location VARCHAR(100) NOT NULL," +
                    "start_date DATE NOT NULL," +
                    "end_date DATE NOT NULL," +
                    "price_per_day DECIMAL(10, 2) NOT NULL," +
                    "FOREIGN KEY (car_id) REFERENCES Car(id)," +
                    "FOREIGN KEY (renter_id) REFERENCES Renter(id)" +
                    ")";
            statement.executeUpdate(createRentalTable);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating tables.");
        }
    }

    // Метод для очищення всіх таблиць
    public static void clearTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Rental");
            statement.executeUpdate("DELETE FROM Renter");
            statement.executeUpdate("DELETE FROM Car");
            System.out.println("All tables have been cleared.");
        }
    }
}
