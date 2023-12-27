package hexlet.code.dto.urls;

import java.util.List;
import java.util.Map;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import hexlet.code.dto.BasePage;

@AllArgsConstructor
@Getter
public class UrlsPage extends BasePage {
    private List<Url> urls;
    private Map<Long, UrlCheck> checkMap;
}
