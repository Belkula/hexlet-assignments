package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(email -> isFreeDomain(email))
                .count();
    }

    private static boolean isFreeDomain(String email) {
        String domain = email.substring(email.indexOf('@') + 1);
        return domain.equals("gmail.com") || domain.equals("yandex.ru") || domain.equals("hotmail.com");
    }
}
// END
