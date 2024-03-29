package exercise;

// BEGIN
import java.util.Map;

public class FileKV implements KeyValueStorage {
    private final String filePath;
    private Map<String, String> data;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        this.data = initialData;
        loadFromFile();
    }

    private void saveToFile() {
        String serializedData = Utils.serialize(data);
        Utils.writeFile(filePath, serializedData);
    }

    private void loadFromFile() {
        String serializedData = Utils.readFile(filePath);
        if (serializedData != null && !serializedData.isEmpty()) {
            this.data = Utils.unserialize(serializedData);
        }
    }

    @Override
    public void set(String key, String value) {
        data.put(key, value);
        saveToFile();
    }

    @Override
    public void unset(String key) {
        data.remove(key);
        saveToFile();
    }

    @Override
    public String get(String key, String defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return data;
    }
}
// END
