package org.example;

import Serializers.JsonEntitySerializer;
import Serializers.XmlEntitySerializer;
import Serializers.YamlEntitySerializer;
import org.example.Car;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "1HGBH41JXMN109186", "ABC123", LocalDate.of(2020, 1, 1), 50000);
        Renter renter = new Renter("Test", "test", "Passport", "ebwhjbfewj");
        Rental rental = new Rental.Builder()
                .setCar(car)
                .setRenter(renter)
                .setPickupLocation("Chernivtsi")
                .setDropoffLocation("Lviv")
                .setStartDate(LocalDate.of(2023, 10, 1))
                .setEndDate(LocalDate.of(2023, 10, 10))
                .setPricePerDay(500)
                .build();

        // Серіалізація і десеріалізація JSON
        testJsonSerializer(car);
        testJsonSerializerRenter(renter);
        testJsonSerializerRental(rental);

        // Серіалізація і десеріалізація XML
        //testXmlSerializer(car);

        // Серіалізація і десеріалізація YAML
        //testYamlSerializer(car);
    }

    private static void testJsonSerializer(Car car) {
        JsonEntitySerializer<Car> jsonSerializer = new JsonEntitySerializer<>();

        try {
            File jsonFile = new File("car.json");

            // Читання з файлу
            Car carFromFileJson = jsonSerializer.readFromFile(jsonFile, Car.class);
            System.out.println("Car read from JSON file:\n" + carFromFileJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testJsonSerializerRenter(Renter renter) {
        JsonEntitySerializer<Renter> jsonSerializer = new JsonEntitySerializer<>();

        try {
            File jsonFile = new File("renter.json");

            // Читання з файлу
            Renter renterFromFileJson = jsonSerializer.readFromFile(jsonFile, Renter.class);
            System.out.println("Renter read from JSON file:\n" + renterFromFileJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testJsonSerializerRental(Rental rental) {
        JsonEntitySerializer<Rental> jsonSerializer = new JsonEntitySerializer<>();

        try {
            File jsonFile = new File("rental.json");
            //jsonSerializer.writeToFile(rental, jsonFile);

            // Читання з файлу
            Rental rentalFromFileJson = jsonSerializer.readFromFile(jsonFile, Rental.class);
            System.out.println("Rental read from JSON file:\n" + rentalFromFileJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testXmlSerializer(Car car) {
        XmlEntitySerializer<Car> xmlSerializer = new XmlEntitySerializer<>();

        try {
            String xmlOutput = xmlSerializer.serialize(car);
            System.out.println("XML Serialization:\n" + xmlOutput);

            Car deserializedCarFromXml = xmlSerializer.deserialize(xmlOutput, Car.class);
            System.out.println("Deserialized XML Car:\n" + deserializedCarFromXml);

            File xmlFile = new File("car.xml");
            xmlSerializer.writeToFile(car, xmlFile);
            System.out.println("Car written to XML file.");

            Car carFromFileXml = xmlSerializer.readFromFile(xmlFile, Car.class);
            System.out.println("Car read from XML file:\n" + carFromFileXml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testYamlSerializer(Car car) {
        YamlEntitySerializer<Car> yamlSerializer = new YamlEntitySerializer<>();

        try {
            String yamlOutput = yamlSerializer.serialize(car);
            System.out.println("YAML Serialization:\n" + yamlOutput);

            Car deserializedCarFromYaml = yamlSerializer.deserialize(yamlOutput, Car.class);
            System.out.println("Deserialized YAML Car:\n" + deserializedCarFromYaml);

            File yamlFile = new File("car.yaml");
            yamlSerializer.writeToFile(car, yamlFile);
            System.out.println("Car written to YAML file.");

            Car carFromFileYaml = yamlSerializer.readFromFile(yamlFile, Car.class);
            System.out.println("Car read from YAML file:\n" + carFromFileYaml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
