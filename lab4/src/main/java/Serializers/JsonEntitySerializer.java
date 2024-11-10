package Serializers;

import Interface.EntitySerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Клас для сериалізації та десериалізації об'єктів у форматі JSON.
 */
public class JsonEntitySerializer<T> implements EntitySerializer<T> {

    private ObjectMapper objectMapper = new ObjectMapper(); // Ініціалізація об'єкта Jackson ObjectMapper

    public JsonEntitySerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    // Метод для сериалізації об'єкта у JSON строку
    @Override
    public String serialize(T entity) throws IOException {
        return objectMapper.writeValueAsString(entity);
    }

    // Метод для десериалізації JSON строки у об'єкт
    @Override
    public T deserialize(String data, Class<T> clazz) throws IOException {
        return objectMapper.readValue(data, clazz);
    }

    // Метод для запису об'єкта у JSON файл
    @Override
    public void writeToFile(T entity, File file) throws IOException {
        objectMapper.writeValue(file, entity);
    }

    // Метод для зчитування об'єкта з JSON файлу
    @Override
    public T readFromFile(File file, Class<T> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }
}
