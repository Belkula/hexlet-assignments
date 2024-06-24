package exercise;

import io.javalin.Javalin;
import java.util.List;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/getPhones", ctx -> {
            List<String> phones = Data.getPhones();
            ctx.json(phones);
        });

        app.get("/getDomains", ctx -> {
            List<String> domains = Data.getDomains();
           ctx.json(domains);
        });

                // Возвращаем настроенное приложение
        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
