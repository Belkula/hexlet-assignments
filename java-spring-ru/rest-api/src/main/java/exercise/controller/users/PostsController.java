package exercise.controller.users;

import java.util.List;
import java.util.stream.Collectors; 
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN

@RestController
@RequestMapping("/api/users")
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable int id) {
        List<Post> userPosts = posts.stream()
                .filter(post -> post.getUserId() == id) 
                .collect(Collectors.toList());

        return ResponseEntity.ok(userPosts);
    }

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createUserPost(@PathVariable int id, @RequestBody Post newPost) {
        newPost.setUserId(id);

        posts.add(newPost);

        return newPost;
    }
}
// END
