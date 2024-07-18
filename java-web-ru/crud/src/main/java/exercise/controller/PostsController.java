package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import exercise.model.Post;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        long postId = ctx.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(postId)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        ctx.render("posts/show.jte", model("post", post));
    }

    public static void index(Context ctx) {
        int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int pageSize = 5;
        List<Post> posts = PostRepository.findAll(page, pageSize);
        PostsPage postsPage = new PostsPage(posts, page);
        ctx.render("posts/index.jte", model("page", postsPage));
    }
    // END
}
