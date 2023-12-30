package hexlet.code.controller;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.Collections;

import hexlet.code.dto.BasePage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.model.Url;
import java.net.URL;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UrlsController {
    public static void build(Context ctx) {
        var page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        try {
            var name = ctx.formParamAsClass("url", String.class).get();
            var startUrl = new URL(name.trim());
            var normalUrl = String.format(
                    "%s://%s%s",
                    startUrl.getProtocol(),
                    startUrl.getHost(),
                    startUrl.getPort() == -1 ? "" : ":" + startUrl.getPort()
            ).toLowerCase();
            var createdAt = new Timestamp(System.currentTimeMillis());
            var url = new Url(normalUrl, createdAt);
            if (!UrlsRepository.findByName(url.getName()).equals(Optional.empty())) {
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.redirect(NamedRoutes.urlsPath());
            } else {
                UrlsRepository.save(url);
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
                ctx.redirect(NamedRoutes.urlsPath());
            }
        } catch (MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect(NamedRoutes.rootPath());
        }
    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlsRepository.getEntities();
        Map<Long, UrlCheck> urlChecks = UrlCheckRepository.findByCheck();
        var page = new UrlsPage(urls, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id: " + id + " not found"));
        List<UrlCheck> urlCheck = UrlCheckRepository.findByUrlId(id);
        var page = new UrlPage(url, urlCheck);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls/show.jte", Collections.singletonMap("page", page));
    }
}
