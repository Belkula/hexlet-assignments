package exercise.dto.posts;

import java.util.List;
import java.util.Map;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import exercise.model.Post;

// BEGIN
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditPostPage {
    private Long id;
    private String name;
    private String body;
    private Map<String, List<ValidationError<Object>>> errors;

    public EditPostPage(Post post, Map<String, List<ValidationError<Object>>> errors) {
        this.id = post.getId();
        this.name = post.getName();
        this.body = post.getBody();
        this.errors = errors;
    }
}
// END
