package exercise;

import java.util.Arrays;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        int numRows = image.length;
        int numCols = (numRows > 0) ? image[0].length : 0;

        String[][] enlargedImage = new String[numRows * 2][numCols * 2];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                String pixel = image[i][j];
                enlargedImage[2 * i][2 * j] = pixel;
                enlargedImage[2 * i][2 * j + 1] = pixel;
                enlargedImage[2 * i + 1][2 * j] = pixel;
                enlargedImage[2 * i + 1][2 * j + 1] = pixel;
            }
        }

        return enlargedImage;
    }
}
// END
