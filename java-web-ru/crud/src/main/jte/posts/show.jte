@import exercise.model.Post

@param Post post

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${post.getName()}</title>
</head>
<body>
    <h1>${post.getName()}</h1>
    <p>${post.getBody()}</p>
    <a href="/posts">вернуться ко всем постам</a>
</body>
</html>
