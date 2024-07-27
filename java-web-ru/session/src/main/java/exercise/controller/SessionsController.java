package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;
import exercise.model.User;
import java.util.Collections;
import exercise.util.NamedRoutes;

import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        ctx.render("build.jte");
    }

    public static void root(Context ctx) {
        String name = ctx.sessionAttribute("currentUser");
        var page = new MainPage(name);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void login(Context ctx) {
        String name = ctx.formParam("name");
        String password = ctx.formParam("password");
        
        if (name == null || password == null) {
            LoginPage page = new LoginPage(name, "Name and password must be provided");
            ctx.render("build.jte", Collections.singletonMap("page", page));
            return;
        }

        String encryptedPassword = encrypt(password);
        User user = UsersRepository.findByName(name);
        
        if (user != null && encryptedPassword.equals(user.getPassword())) {
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            LoginPage page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", Collections.singletonMap("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
