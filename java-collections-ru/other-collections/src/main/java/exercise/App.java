package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
public class App {
    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> result = new LinkedHashMap<>();

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        allKeys.forEach(key -> {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (value1 != null && value2 != null && value1.equals(value2)) {
                result.put(key, "unchanged");
            } else if (value1 != null && value2 != null) {
                result.put(key, "changed");
            } else if (value1 != null) {
                result.put(key, "deleted");
            } else {
                result.put(key, "added");
            }
        });

        return result;
    }
}
//END
