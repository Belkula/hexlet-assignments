@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page (content = @`
    <div class="container">
        <h1>All Posts</h1>
        <a href="${NamedRoutes.buildPostPath()}" class="btn btn-primary mb-3">Create New Post</a>
        @if (page.getPosts().isEmpty())
            <p>No posts available.</p>
        @else
            <ul class="list-group">
                @for (Post post : page.getPosts())
                    <li class="list-group-item">
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                    </li>
                @endfor
            </ul>
        @endif
    </div>
`)
