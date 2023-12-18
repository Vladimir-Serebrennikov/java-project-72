package gg.jte.generated.ondemand.products;
import hexlet.code.dto.products.ProductPage;
public final class JteshowGenerated {
	public static final String JTE_NAME = "products/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,6,6,8,8,8,12,12,12,14,14,14};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ProductPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"mb-3\">\n        <h1>");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getProduct().getTitle());
				jteOutput.writeContent("</h1>\n    </div>\n\n    <div class=\"mb-3\">\n        ");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(page.getProduct().getPrice());
				jteOutput.writeContent("\n    </div>\n");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ProductPage page = (ProductPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
