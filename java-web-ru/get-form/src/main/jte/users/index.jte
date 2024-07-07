@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page( content = @`
    <h1>Users</h1>
    <form action="/users" method="get">
    <input type="search" required name="firstName" value="${page.getFullName()}">
    <input type="submit" value="search">
</form>

    <ul>
    @for(var user : page.getUsers()) 
        <li><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></li>
    @endfor
    </ul>
`)