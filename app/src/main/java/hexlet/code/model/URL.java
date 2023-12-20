package hexlet.code.model;

import lombok.Getter;
import java.sql.Timestamp;

@Getter
public class URL {
    private Long id;

    private String name;

    private Timestamp createdAt;

    public URL(String name, Timestamp createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }
}
