package hexlet.code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.model.Url;

class AppTest {

    private static Javalin app;

    @BeforeEach
    public void setUp() throws Exception {
        app = App.getApp();
    }

    @Test
    void testMainPage() throws Exception {

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testProductsPage() throws Exception {

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/products");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testProductPage() throws Exception {
        var product = new Url("car", 100);
        UrlsRepository.save(product);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/products/" + product.getId());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testBuildProductPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/products/build");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testCreateProduct() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "title=car&price=100";
            var response = client.post("/products", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("car");
        });

        assertThat(UrlsRepository.getEntities()).hasSize(1);
    }

}
