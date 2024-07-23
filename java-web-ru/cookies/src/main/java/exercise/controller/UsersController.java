package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        String token = Security.generateToken();

        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        ctx.cookie("user-token", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) {
        Long userId = Long.parseLong(ctx.pathParam("id"));
        User user = UserRepository.find(userId).orElseThrow(() -> new NotFoundResponse("User not found"));

        String token = ctx.cookie("user-token");
        if (token == null || !token.equals(user.getToken())) {
            ctx.redirect(NamedRoutes.buildUserPath());
            return;
        }

        ctx.render("users/show.jte", model("page", new UserPage(user)));
    }
    // END
}
