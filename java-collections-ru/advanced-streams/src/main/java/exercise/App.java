package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String content) {
        return Arrays.stream(content.split("\n"))
                .filter(line -> line.contains("environment"))
                .flatMap(line -> Arrays.stream(line.split("\"")))
                .skip(1)
                .findFirst()
                .map(variables -> Arrays.stream(variables.split(","))
                        .filter(variable -> variable.startsWith("X_FORWARDED_"))
                        .map(variable -> {
                            String[] keyValue = variable.split("=");
                            if (keyValue.length == 2) {
                                return keyValue[0].substring("X_FORWARDED_".length());
                            }
                            return "";
                        })
                        .collect(Collectors.joining(",")))
                .orElse("");
    }
}
//END
