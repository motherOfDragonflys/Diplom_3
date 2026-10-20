package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pageobjects.ConstructorPage;
import static pageobjects.ConstructorPage.*;
import static org.junit.Assert.assertTrue;

@Feature("Раздел «Конструктор»")
@RunWith(Parameterized.class)
public class ConstructorSectionParameterizedTest extends BaseTest {

    //Название вкладки, вкладка старта, её маркер активности, проверяемая вкладка, её маркер активности
    private final String name;
    private final By tabToPrepare;
    private final By prepareTabActive;
    private final By tabToClick;
    private final By tabToAssert;

    public ConstructorSectionParameterizedTest(String name, By tabToPrepare, By prepareTabActive,
                                               By tabToClick, By tabToAssert) {
        this.name = name;
        this.tabToPrepare = tabToPrepare;
        this.prepareTabActive = prepareTabActive;
        this.tabToClick = tabToClick;
        this.tabToAssert = tabToAssert;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] sections() {
        return new Object[][]{
                // Соусы: стартуем с Булок, кликаем Соусы, ждём SAUCES_TAB_ACTIVE
                {"Отображение соусов для вкладки Соусы",
                        BUNS_TAB,     BUNS_TAB_ACTIVE,
                        SAUCES_TAB,   SAUCES_TAB_ACTIVE},
                // Начинки: стартуем с Булок, кликаем Начинки, ждём FILLINGS_TAB_ACTIVE
                {"Отображение начинок для вкладки Начинки",
                        BUNS_TAB,     BUNS_TAB_ACTIVE,
                        FILLINGS_TAB, FILLINGS_TAB_ACTIVE},
                // Булки: стартуем с Соусов, кликаем Булки, ждём BUNS_TAB_ACTIVE
                {"Отображение булок для вкладки Булки",
                        SAUCES_TAB,   SAUCES_TAB_ACTIVE,
                        BUNS_TAB,     BUNS_TAB_ACTIVE},
        };
    }

    @Before


    @Test
    @Description("Тесты отображения для вкладок конструктора раздела соответствующим заголовком")
    public void sectionTransitionTest() {

        Allure.getLifecycle().updateTestCase(t -> t.setName(name));

        ConstructorPage page = new ConstructorPage(driver);

        // Открыли раздел с конструктором
        page.openConstructor();
        // Сначала уходим на другую вкладку (для булок - не булки), чтобы проверяемый переход был настоящим
        page.clickTab(tabToPrepare, prepareTabActive);
        // Переходим к проверяемому разделу
        page.clickTab(tabToClick, tabToAssert);

        assertTrue("Активной является вкладка \"" + name + "\"",
                page.isTabActive(tabToAssert));
    }
}
