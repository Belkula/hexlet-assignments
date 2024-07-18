
@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

<!doctype html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<main>
    @if(page != null && page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>
                        ${error.getMessage()}
                    </li>
                @endfor
            @endfor
        </ul>
    @endif
    <h4>новая статья</h4>
    <form action="/articles" method="post">
        <div>
            <div>Название</div>
            <input required type="text" name="title" value="${page != null ? page.getTitle() : ""}">
        </div>
        <div>
            <div>Содержимое</div>
            <textarea required name="content" rows="50" cols="50">${page != null ? page.getContent() : ""}</textarea>
        </div>
        <div>
            <input type="submit" value="Создать новую статью">
        </div>
    </form>
</main>
</body>
</html>
