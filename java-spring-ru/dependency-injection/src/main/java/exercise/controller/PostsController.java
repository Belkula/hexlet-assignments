package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager entityManager;

    @GetMapping("")
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post show(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Post with id " + id + " not found"));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        // Выполняем проверку через JPQL
        String jpql = "SELECT p FROM Post p WHERE p.title = :title AND p.body = :body";
        TypedQuery<Post> query = entityManager.createQuery(jpql, Post.class);
        query.setParameter("title", post.getTitle());
        query.setParameter("body", post.getBody());
        List<Post> existingPosts = query.getResultList();
    
        if (existingPosts.isEmpty()) {
            return postRepository.save(post);
        } else {
            throw new ResourceNotFoundException("Found same post");
        }
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        java.util.Optional<Post> foundObject = postRepository.findById(id);
        if (foundObject.isPresent()) {
            Post updatedPost = foundObject.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setBody(post.getBody());
            return updatedPost;
        } else throw new ResourceNotFoundException("Post not found in bd!");
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        java.util.Optional<Post> soughtPost = postRepository.findById(id);
        soughtPost.ifPresent(post -> {
            postRepository.delete(post);
            commentRepository.deleteByPostId(id);
        });
    }
}
// END
