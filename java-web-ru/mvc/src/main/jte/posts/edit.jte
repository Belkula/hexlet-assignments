@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
</head>
<body>
    <h1>Edit Post</h1>
    <form action="${NamedRoutes.updatePostPath(page.getId())}" method="post">
        <input type="hidden" name="_method" value="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${page.getName()}">
        <div>
            @if (page.getErrors() != null && page.getErrors().containsKey("name")) 
                <p style="color: red;">${page.getErrors().get("name").get(0).getMessage()}</p>
            @endif
        </div>
        <label for="body">Body:</label>
        <textarea id="body" name="body">${page.getBody()}</textarea>
        <div>
            @if (page.getErrors() != null && page.getErrors().containsKey("body")) 
                <p style="color: red;">${page.getErrors().get("body").get(0).getMessage()}</p>
            @endif
        </div>
        <button type="submit">Update</button>
    </form>
</body>
</html>
