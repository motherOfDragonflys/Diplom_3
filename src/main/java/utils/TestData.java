package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Генератор тестовых данных. */
public class TestData {

    private static final DateTimeFormatter TS =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    // Уникальный email на каждый прогон: test-20261014132325@example.com
    public static String uniqueEmail() {
        return "test-" + LocalDateTime.now().format(TS) + "@example.com";
    }

    // Валидный пароль (шесть и более символов)
    public static String validPassword() {
        return "paSSword1";
    }

    // Имя пользователя
    public static String userName() {
        return "Тестовый котик";
    }
}
