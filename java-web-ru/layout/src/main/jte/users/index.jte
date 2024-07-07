@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(title = "User List", content = @`
    <h1>Users</h1>
    <ul>
    @for(var user : page.getUsers()) 
        <li><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></li>
    @endfor
    </ul>
`)