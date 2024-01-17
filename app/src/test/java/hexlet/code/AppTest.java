package hexlet.code;
import hexlet.code.repository.UrlCheckRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.model.Url;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

class AppTest {
    Javalin app;
    private static MockWebServer mockWebServer;
    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }
    @BeforeAll
    public static void startWebServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    public static void stopWebServer() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlPageWithMockedServer() throws IOException, SQLException {
        var file = "./src/test/resources/page.html";
        var body = Files.readString(Paths.get(file));
        mockWebServer.enqueue(new MockResponse().setBody(body));
        var serverUrl = mockWebServer.url("/").toString();
        var actualUrl = new Url(serverUrl);
        UrlsRepository.save(actualUrl);
        JavalinTest.test(app, (server, client) -> {
            client.post("/urls/" + actualUrl.getId() + "/checks");
            var response = client.get("/urls/" + actualUrl.getId());
            assertThat(response.code()).isEqualTo(200);
        });
        var checkUrl = UrlCheckRepository.findByCheck().get(actualUrl.getId());
        assertThat(checkUrl).isNotNull();
        assertThat(checkUrl.getTitle()).isEqualTo("Тест анализатора страниц");
        assertThat(checkUrl.getH1()).isEqualTo("Тестируем анализатор страниц!");
        String expectedDescription = "Четвёртый проект – полноценный веб-сайт на базе фреймворка Javalin. "
                + "Здесь отрабатываются базовые принципы построения современных сайтов на MVC-архитектуре.";
        assertThat(checkUrl.getDescription()).isEqualTo(expectedDescription);

    }

    @Test
    public void testSaveUrl() {
        var url = "https://ru.hexlet.io";
        JavalinTest.test(app, (server, client) -> {
            var reqBody = "url=" + url;
            var response = client.post("/urls", reqBody);
            assertThat(response.code()).isEqualTo(200);
            var actualUrl = UrlsRepository.findByName(url).orElse(null);
            assertThat(actualUrl).isNotNull();
            assertThat(actualUrl.getName()).isEqualTo(url);
        });
    }

    @Test
    public void testUncorrectUrl() {
        JavalinTest.test(app, (server, client) -> {
            var reqBody = "url=abracadabra.com";
            var response = client.post("/urls", reqBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/1");
            assertThat(response.code()).isEqualTo(404);
        });
    }
}
