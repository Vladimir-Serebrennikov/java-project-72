package hexlet.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;
import hexlet.code.controller.UrlsController;
import hexlet.code.controller.RootController;
import hexlet.code.util.NamedRoutes;
import hexlet.code.repository.BaseRepository;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import gg.jte.resolve.ResourceCodeResolver;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Slf4j
public final class App {

    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        String jdbcUrl = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project");
        hikariConfig.setJdbcUrl(jdbcUrl);

        JavalinJte.init(createTemplateEngine());

        var dataSource = new HikariDataSource(hikariConfig);
        var sql = readSchemaFile();

        log.info(sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get(NamedRoutes.rootPath(), RootController::index);
        app.post(NamedRoutes.urlsPath(), UrlsController::create);

        return app;
    }

    private static String readSchemaFile() throws IOException {
        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("schema.sql");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    public static void main(String[] args) throws IOException, SQLException {
        Javalin app = getApp();
        app.start(7070);
    }
}
