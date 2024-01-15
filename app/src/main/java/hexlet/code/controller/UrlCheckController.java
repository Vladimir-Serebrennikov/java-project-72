package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

import java.rmi.ConnectException;
import java.sql.SQLException;
import java.sql.Timestamp;

@Slf4j
public class UrlCheckController {
    public static void create(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        try {
            var url = UrlsRepository.findById(urlId)
                    .orElseThrow(() -> new NotFoundResponse("Entity with id: " + urlId + " not found"));
            var response = Unirest.get(url.getName()).asString();
            var document = Jsoup.parse(response.getBody());
            var statusCode = response.getStatus();
            var createdAt = new Timestamp(System.currentTimeMillis());
            var title = document.title();
            var h1 = document.selectFirst("h1") != null
                    ? document.selectFirst("h1").text() : "";
            var description = document.selectFirst("meta[name=description]") != null
                    ? document.selectFirst("meta[name=description]").attr("content") : "";
            log.info("h1: " + h1);
            log.info("description: " + description);
            var urlCheck = new UrlCheck(statusCode, title, h1, description, urlId, createdAt);
            UrlCheckRepository.save(urlCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверена");
        }  catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
        } finally {
            ctx.redirect(NamedRoutes.urlPath(urlId));
        }
    }
}
