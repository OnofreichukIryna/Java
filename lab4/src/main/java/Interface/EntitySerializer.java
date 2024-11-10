package Interface;

import java.io.File;
import java.io.IOException;

public interface EntitySerializer<T> {

    // Метод для сериалізації об'єкта у строку
    String serialize(T entity) throws IOException;

    // Метод для десериалізації об'єкта з строки
    T deserialize(String data, Class<T> clazz) throws IOException;

    // Метод для запису об'єкта у файл
    void writeToFile(T entity, File file) throws IOException;

    // Метод для зчитування об'єкта з файлу
    T readFromFile(File file, Class<T> clazz) throws IOException;
}
