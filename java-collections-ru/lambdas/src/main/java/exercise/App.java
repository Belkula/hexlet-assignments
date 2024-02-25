package exercise;

import java.util.Arrays;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .flatMap(row -> Arrays.stream(row)
                        .flatMap(pixel -> Arrays.stream(new String[]{pixel, pixel})))
                .toArray(size -> new String[image.length * 2][image[0].length * 2]);
    }
}
// END
