package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SEOTestTypes {
    HEADINGS("Заголовки H1-H6"),
    IMAGES("Изображения"),
    META_TITLE("Заголовок (Meta)"),
    META_DESCRIPTION("Описание (Meta)"),
    LINKS("Ссылки на странице"),
    CONTENT("Контент"),
    TECH_PARAMS("Технические параметры"),
    MICRODATA("Микроразметка"),
    AIRECOMMENDATION("Рекомендации ИИ");

    private final String name;
}