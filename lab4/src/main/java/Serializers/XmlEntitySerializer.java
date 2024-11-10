package Serializers;

import Interface.EntitySerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;

/**
 * Клас для сериалізації та десериалізації об'єктів у форматі XML.
 */
public class XmlEntitySerializer<T> implements EntitySerializer<T> {

    private XmlMapper xmlMapper = new XmlMapper(); // Ініціалізація об'єкта XmlMapper

    public XmlEntitySerializer() {
        xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule()); // Додаємо підтримку Java 8 Date/Time
    }

    // Метод для сериалізації об'єкта у XML строку
    @Override
    public String serialize(T entity) throws IOException {
        return xmlMapper.writeValueAsString(entity);
    }

    // Метод для десериалізації XML строки у об'єкт
    @Override
    public T deserialize(String data, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(data, clazz);
    }

    // Метод для запису об'єкта у XML файл
    @Override
    public void writeToFile(T entity, File file) throws IOException {
        xmlMapper.writeValue(file, entity);
    }

    // Метод для зчитування об'єкта з XML файлу
    @Override
    public T readFromFile(File file, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(file, clazz);
    }
}
