package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
public class UrlCheck {
    @Setter
    private long id;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    @Setter
    private long urlId;
    private Timestamp createdAt;

    public UrlCheck(int statusCode, String title, String h1, String description, Timestamp createdAt) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.createdAt = createdAt;
    }

    public UrlCheck(int statusCode, String title, String h1, String description, long urlId, Timestamp createdAt) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.urlId = urlId;
        this.createdAt = createdAt;
    }
}
