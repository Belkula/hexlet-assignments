@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page = new exercise.dto.LoginPage(null, null)

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
                @if(page.getError() != null)
            <p>${page.getError()}</p>
        @endif
            <h1>Login</h1>
            <form action="${NamedRoutes.loginPath()}" method="post">
                <div class="mb-3">
                    <label class="form-label">
                        Username
                        <input type="text" class="form-control" name="name" required />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">
                        Password
                        <input type="password" class="form-control" name="password" required />
                    </label>
                </div>
                <input type="submit" class="btn btn-primary" value="Login" />
            </form>
        </div>
    `
)
