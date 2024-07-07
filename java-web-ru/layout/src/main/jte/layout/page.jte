@import gg.jte.Content
@param Content content
@param String title

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        ${title}
    </title>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/users">Users</a></li>
            </ul>
        </nav>
    </header>

    <main>
        ${content}
    </main>

    <footer>
        <p>Footer example</p>
    </footer>
</body>
</html>
