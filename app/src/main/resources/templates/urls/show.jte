@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(

content = @`
    <div class="mb-3">
        <h1>${page.getProduct().getTitle()}</h1>
    </div>

    <div class="mb-3">
        ${page.getProduct().getPrice()}
    </div>
`
)