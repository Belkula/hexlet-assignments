@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
</head>
<body>
        <h1>Таблица пользователей</h1>
        @if(usersPage.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользователя</p>
        @else
            @for(var user : usersPage.getUsers())
               <div>
                   <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
               </div>
            @endfor
        @endif
</body>
</html>