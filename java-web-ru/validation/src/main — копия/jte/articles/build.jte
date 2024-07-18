@template page(BuildArticlePage page)
@import java.util.List

<h1>New Article</h1>
<form action="/articles" method="post">
    <div>
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${page.getTitle()}">
        <div style="color: red;">
            @if(!page.getErrors().isEmpty()) {
                @for(String error : page.getErrors()) {
                    @if(error.contains("Название")) {
                        ${error}<br>
                    }
                    @endif
                }
                @endfor
            }
            @endif
        </div>
    </div>
    <div>
        <label for="content">Content:</label>
        <textarea id="content" name="content">${page.getContent()}</textarea>
        <div style="color: red;">
            @if(!page.getErrors().isEmpty()) {
                @for(String error : page.getErrors()) {
                    @if(error.contains("Статья")) {
                        ${error}<br>
                    }
                    @endif
                }
                @endfor
            }
            @endif
        </div>
    </div>
    <div>
        <button type="submit">Create</button>
    </div>
</form>
