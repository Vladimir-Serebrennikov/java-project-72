package hexlet.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;
import hexlet.code.controller.ProductsController;
import hexlet.code.controller.RootController;
import hexlet.code.util.NamedRoutes;
import hexlet.code.repository.BaseRepository;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Slf4j
public final class App {

    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet;DB_CLOSE_DELAY=-1;");

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
        app.get(NamedRoutes.buildProductPath(), ProductsController::build);
        app.post(NamedRoutes.productsPath(), ProductsController::create);
        app.get(NamedRoutes.productsPath(), ProductsController::index);
        app.get(NamedRoutes.productPath("{id}"), ProductsController::show);

        return app;
    }

    private static String readSchemaFile() throws IOException {
        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("schema.sql");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        Javalin app = getApp();
        app.start(7070);
    }
}
