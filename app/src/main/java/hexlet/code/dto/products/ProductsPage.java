package hexlet.code.dto.products;

import java.util.List;
import hexlet.code.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import hexlet.code.dto.BasePage;

@AllArgsConstructor
@Getter
public class ProductsPage extends BasePage {
    private List<Product> products;
}
