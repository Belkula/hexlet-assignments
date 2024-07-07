@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page( title = "User data", content = @`
    <h1>User Details</h1>
    <p> ID: ${page.getUser().getId()}</p>
    <p> Name: ${page.getUser().getFirstName()} ${page.getUser().getLastName()}</p>
    <p> Email: ${page.getUser().getEmail()}</p>
`)
