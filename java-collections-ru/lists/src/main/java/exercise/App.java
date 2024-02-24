package exercise;

import java.util.Arrays;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String letters, String word) {
        String lowerCaseWord = word.toLowerCase();
        String lowerCaseLetters = letters.toLowerCase();

        List<Character> letterList = Arrays.asList(lowerCaseLetters.chars().mapToObj(c -> (char) c).toArray(Character[]::new));

        for (char c : lowerCaseWord.toCharArray()) {
            if (!letterList.remove(Character.valueOf(c))) {
                return false;
            }
        }
        return true; 
    }
}
// END