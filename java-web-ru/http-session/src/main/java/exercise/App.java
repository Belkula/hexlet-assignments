package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            int perPage = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            List<Map<String, String>> paginatedUsers = getUsersForPage(page, perPage);

            ctx.json(paginatedUsers);
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }

    private static List<Map<String, String>> getUsersForPage(int page, int perPage) {
        int fromIndex = (page - 1) * perPage;
        int toIndex = Math.min(fromIndex + perPage, USERS.size());

        if (fromIndex > USERS.size()) {
            return List.of();
        }

        return USERS.subList(fromIndex, toIndex);
    }
}
