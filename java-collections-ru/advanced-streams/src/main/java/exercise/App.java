package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String content) {
        return Arrays.stream(content.split("\n"))
                .filter(line -> line.contains("environment"))
                .findFirst()
                .map(line -> {
                    String[] variables = line.split("\"")[1].split(",");
                    return Arrays.stream(variables)
                            .filter(variable -> variable.startsWith("X_FORWARDED_"))
                            .map(variable -> {
                                String[] keyValue = variable.split("=");
                                if (keyValue.length == 2) {
                                    return keyValue[0].substring("X_FORWARDED_".length()) + "=" + keyValue[1];
                                }
                                return "";
                            })
                            .collect(Collectors.joining(","));
                })
                .orElse("");
    }
}
//END
