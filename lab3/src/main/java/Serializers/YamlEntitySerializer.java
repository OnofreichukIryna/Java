package Serializers;

import Interface.EntitySerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

/**
 * Клас для сериалізації та десериалізації об'єктів у форматі YAML.
 */
public class YamlEntitySerializer<T> implements EntitySerializer<T> {

    private final ObjectMapper objectMapper; // Ініціалізація об'єкта Jackson ObjectMapper

    public YamlEntitySerializer() {
        // Використання YAMLFactory для обробки YAML
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.registerModule(new JavaTimeModule()); // Додати підтримку Java 8 дати/часу
    }

    // Метод для сериалізації об'єкта у YAML строку
    @Override
    public String serialize(T entity) throws IOException {
        return objectMapper.writeValueAsString(entity);
    }

    // Метод для десериалізації YAML строки у об'єкт
    @Override
    public T deserialize(String data, Class<T> clazz) throws IOException {
        return objectMapper.readValue(data, clazz);
    }

    // Метод для запису об'єкта у YAML файл
    @Override
    public void writeToFile(T entity, File file) throws IOException {
        objectMapper.writeValue(file, entity);
    }

    // Метод для зчитування об'єкта з YAML файлу
    @Override
    public T readFromFile(File file, Class<T> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }
}
