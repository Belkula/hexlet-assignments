package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
// BEGIN
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

public class App {
    
    public static void save(Path filePath, Car car) throws IOException {
        String json = car.serialize();
        Files.writeString(filePath, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Машина успешно сохранена");
    }

    public static Car extract(Path filePath) throws IOException {
        String json = Files.readString(filePath);
        return Car.unserialize(json);
    }
}
// END
