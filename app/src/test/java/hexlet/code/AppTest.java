package hexlet.code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.model.Url;

import java.io.IOException;
import java.sql.SQLException;

class AppTest {
    Javalin app;
    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }
    @Test
    public void testMainPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        }));
    }
}
