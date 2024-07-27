@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
    content = @`
            @if (page.getName()  != null) 
                    <div>Пользователь: ${String.valueOf(page.getName())}</div>
                    <form action="${NamedRoutes.logoutPath()}" method="post">
                        <input type="submit" class="btn btn-danger" value="Выйти" />
                    </form>
             @else 
                    <a href="${NamedRoutes.buildSessionPath()}" class="btn btn-primary">Войти</a>
                    <a href="${NamedRoutes.buildSessionPath()}" class="btn btn-primary">Войти</a>
            @endif
    `
)
