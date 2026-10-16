package locators;

import org.openqa.selenium.By;

public class ConstructorLocators {
    private ConstructorLocators() {}

//__ВКЛАДКИ КОНСТРУКТОРА
    // Вкладка Булки
    public static final By BUNS_TAB =
            By.xpath("//div[contains(@class,'tab')][.//span[text()='Булки']]");

    // Вкладка Начинки
    public static final By FILLINGS_TAB =
            By.xpath("//div[contains(@class,'tab')][.//span[text()='Начинки']]");

    // Вкладка Соусы
    public static final By SAUCES_TAB =
            By.xpath("//div[contains(@class,'tab')][.//span[text()='Соусы']]");

//__ЛОКАТОРЫ АКТИВНОЙ ВКЛАДКИ КОНСТРУКТОРА

    // Любая активная вкладка (маркер, что вкладки отрисованы)
    public static final By ACTIVE_TAB =
            By.cssSelector(".tab_type_current");

    // Активная вкладка Булки
    public static final By BUNS_TAB_ACTIVE =
            By.xpath(
                    "//div[contains(@class,'tab') and contains(@class,'tab_type_current')][.//span[text()='Булки']]");

    // Активная вкладка Начинки
    public static final By FILLINGS_TAB_ACTIVE =
            By.xpath(
                    "//div[contains(@class,'tab') and contains(@class,'tab_type_current')][.//span[text()='Начинки']]");

    // Активная вкладка Соусы
    public static final By SAUCES_TAB_ACTIVE =
            By.xpath(
                    "//div[contains(@class,'tab') and contains(@class,'tab_type_current')][.//span[text()='Соусы']]");

    // Булка как маркер загрузки всех элементов конструктора
    public static final By FIRST_BUN_CARD =
            By.xpath(".//p[contains(text(),'Краторная булка')]");

//__Кнопка "Оформить заказ" в конструкторе

    public static final By ORDER_BUTTON =
            By.xpath(".//button[normalize-space()='Оформить заказ']");


}
