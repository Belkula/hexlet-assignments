@import exercise.dto.users.UserPage
@param UserPage userPage

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <h2>${userPage.getUser().getFirstName()} ${userPage.getUser().getLastName()}</h2>
        <div>${userPage.getUser().getEmail()}</div>
    </main>
</div>
</body>
</html>
