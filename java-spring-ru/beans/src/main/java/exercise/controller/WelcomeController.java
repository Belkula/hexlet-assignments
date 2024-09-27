package exercise.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
import exercise.daytime.Daytime;

@RestController
public class WelcomeController {

    private final Daytime currentDaytime;

    public WelcomeController(Daytime currentDaytime) {
        this.currentDaytime = currentDaytime;
    }

    @GetMapping("/welcome")
    public String welcome() {
        String daytimeName = currentDaytime.getName();
        return "It is " + daytimeName + " now! Welcome to Spring!";
    }
}
// END
