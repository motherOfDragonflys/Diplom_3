package utils;

/**
 * Конфигурация стенда.
 * <p>
 * Адрес переопределяется системным свойством {@code base.url}:
 * <pre>
 *   mvn clean test -Dbase.url=https://другой-стенд.ru
 * </pre>
 * По умолчанию — прод-стенд из задания.
 */
public final class Config {

    private static final String BASE_URL_DEFAULT =
            "https://stellarburgers.nomoreparties.site";

    private Config() {
    }

    public static String baseUrl() {
        return System.getProperty("base.url", BASE_URL_DEFAULT);
    }
}
