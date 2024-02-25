package exercise;

import java.util.Arrays;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        int numRows = image.length;
        int numCols = (numRows > 0) ? image[0].length : 0;

        String[][] enlargedImage = new String[numRows * 2][numCols * 2];

        Arrays.stream(image).forEach((row, i) -> {
            Arrays.stream(row).forEach((pixel, j) -> {
                enlargedImage[2*i][2*j] = pixel;
                enlargedImage[2*i][2*j + 1] = pixel;
                enlargedImage[2*i + 1][2*j] = pixel;
                enlargedImage[2*i + 1][2*j + 1] = pixel;
            });
        });

        return enlargedImage;
    }
}

// END
