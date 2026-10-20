package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pageobjects.ConstructorPage;
import static pageobjects.ConstructorPage.*;
import static org.junit.Assert.assertTrue;

@Feature("Раздел Конструктор")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RunWith(Parameterized.class)
public class ConstructorSectionParameterizedTest extends BaseTest {

    // Имя стартовой вкладки, имя проверяемой вкладки
    private final String startName;
    private final String targetName;
    // Вкладка старта, ее маркер активности
    private final By tabToPrepare;
    private final By prepareTabActive;
    // Проверяемая вкладка, ее маркер активности
    private final By tabToClick;
    private final By tabToAssert;

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] sections() {
        return new Object[][]{
                // Соусы: стартуем с Булок, кликаем Соусы, ждём SAUCES_TAB_ACTIVE
                {"Булки", "Соусы",
                        BUNS_TAB,     BUNS_TAB_ACTIVE,
                        SAUCES_TAB,   SAUCES_TAB_ACTIVE},
                // Начинки: стартуем с Булок, кликаем Начинки, ждём FILLINGS_TAB_ACTIVE
                {"Булки", "Начинки",
                        BUNS_TAB,     BUNS_TAB_ACTIVE,
                        FILLINGS_TAB, FILLINGS_TAB_ACTIVE},
                // Булки: стартуем с Соусов, кликаем Булки, ждём BUNS_TAB_ACTIVE
                {"Соусы", "Булки",
                        SAUCES_TAB,   SAUCES_TAB_ACTIVE,
                        BUNS_TAB,     BUNS_TAB_ACTIVE},
        };
    }

    @Test
    @Description("Проверка перехода по вкладкам конструктора и отображения соответствующей секции: \n" +
            "Сначала переходим на другую вкладку, затем кликаем проверяемую, которая точно не выбрана")
    public void sectionTransitionTest() {

        Allure.getLifecycle().updateTestCase(t ->
                t.setName("Отображение ингредиентов для вкладки " + targetName));

        ConstructorPage page = new ConstructorPage(driver);

        page.openConstructor();
        page.clickTab(startName, tabToPrepare, prepareTabActive);
        // Переходим к проверяемому разделу: клик по вкладке, проверка активного раздела в констукторе
        page.clickTab(targetName, tabToClick, tabToAssert);

        assertTrue("Вкладка " + targetName + " не активна, секция не отображается",
                page.isTabActive(targetName, tabToAssert));
    }
}
