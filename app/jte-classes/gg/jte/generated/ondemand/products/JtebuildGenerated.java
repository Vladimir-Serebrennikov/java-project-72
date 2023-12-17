package gg.jte.generated.ondemand.products;
import hexlet.code.dto.products.BuildProductPage;
import hexlet.code.util.NamedRoutes;;
public final class JtebuildGenerated {
	public static final String JTE_NAME = "products/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,7,7,8,8,11,11,12,12,13,13,13,14,14,15,15,18,18,21,21,21,21,21,21,21,21,25,25,25,25,25,25,25,25,31,31,31,31,31,31,31,31,37,37,37};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BuildProductPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				if (page.getErrors() != null) {
					jteOutput.writeContent("\n        <div class=\"mb-3\">\n            <ul>\n                ");
					for (var validator : page.getErrors().values()) {
						jteOutput.writeContent("\n                    ");
						for (var error : validator) {
							jteOutput.writeContent("\n                        <li>");
							jteOutput.setContext("li", null);
							jteOutput.writeUserContent(error.getMessage());
							jteOutput.writeContent("</li>\n                    ");
						}
						jteOutput.writeContent("\n                ");
					}
					jteOutput.writeContent("\n            </ul>\n        </div>\n    ");
				}
				jteOutput.writeContent("\n\n    <div class=\"mx-auto p-4 py-md-5\">\n        <form");
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(NamedRoutes.productsPath())) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(NamedRoutes.productsPath());
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\n            <div class=\"mb-3\">\n                <label class=\"form-label\">\n                    Название\n                    <input type=\"text\" class=\"form-control\" name=\"title\"");
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(page.getTitle())) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(page.getTitle());
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\n                </label>\n            </div>\n            <div class=\"mb-3\">\n                <label class=\"form-label\">\n                    Цена\n                    <input type=\"text\" class=\"form-control\" name=\"price\"");
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(page.getPrice())) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(page.getPrice());
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\n                </label>\n            </div>\n            <input type=\"submit\" class=\"btn btn-primary\" value=\"Сохранить\" />\n        </form>\n    </div>\n");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BuildProductPage page = (BuildProductPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
