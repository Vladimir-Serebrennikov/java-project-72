package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    private Long id;

    @ToString.Include
    private String title;

    private int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
