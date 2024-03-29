package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> homes, int n) {
        return homes.stream()
                .sorted((home1, home2) -> Double.compare(home1.getArea(), home2.getArea()))
                .limit(n)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
