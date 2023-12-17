package hexlet.code.dto.products;

import hexlet.code.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductPage  {
    private Product product;
}
