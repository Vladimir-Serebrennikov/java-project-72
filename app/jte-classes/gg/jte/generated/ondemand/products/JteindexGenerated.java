package gg.jte.generated.ondemand.products;
import hexlet.code.dto.products.ProductsPage;
import hexlet.code.util.NamedRoutes;
public final class JteindexGenerated {
	public static final String JTE_NAME = "products/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,8,8,11,11,11,11,11,11,11,11,14,14,17,17,17,20,20,20,20,20,20,20,20,20,20,20,23,23,23,26,26,28,28,28};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ProductsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <h1>Все товары</h1>\n    <div class=\"mb-3\">\n        <a");
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(NamedRoutes.buildProductPath())) {
					jteOutput.writeContent(" href=\"");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(NamedRoutes.buildProductPath());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">Добавить новый товар</a>\n    </div>\n    <table class=\"table table-striped\">\n        ");
				for (var product : page.getProducts()) {
					jteOutput.writeContent("\n            <tr>\n                <td>\n                    ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(product.getId());
					jteOutput.writeContent("\n                </td>\n                <td>\n                    <a");
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(NamedRoutes.productPath(product.getId()))) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(NamedRoutes.productPath(product.getId()));
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(product.getTitle());
					jteOutput.writeContent("</a>\n                </td>\n                <td>\n                    ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(product.getPrice());
					jteOutput.writeContent("\n                </td>\n            </tr>\n        ");
				}
				jteOutput.writeContent("\n    </table>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ProductsPage page = (ProductsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
