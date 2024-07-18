@import exercise.model.Post
@import exercise.util.NamedRoutes

@param exercise.dto.posts.PostsPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
</head>
<body>
    <h1>Посты</h1>
    <ul>
        @for (Post post : page.getPosts()) 
            <li>
                <a href="${NamedRoutes.postPath(String.valueOf(post.getId()))}">${post.getName()}</a>
            </li>
        
        @endfor
    </ul>
    <div>
        @if (page.getPage() > 1) 
            <a href="${NamedRoutes.postsPath()}?page=${page.getPage() - 1}">Previous</a>
        
        @endif
        <a href="${NamedRoutes.postsPath()}?page=${page.getPage() + 1}">Next</a>
    </div>
</body>
</html>
